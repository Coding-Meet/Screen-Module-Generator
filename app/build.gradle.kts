import java.util.Locale

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.example.screenmodulegenerator"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.screenmodulegenerator"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    implementation("io.coil-kt:coil-compose:2.6.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

tasks.register("createFeature") {
    val featureName = project.findProperty("featureName") as String?
        ?: throw IllegalArgumentException("Feature name is required")

    val templateType = project.findProperty("templateType") as String?
        ?: throw IllegalArgumentException("Template Type is required")


    // Retrieve the package name from the project configuration
    val basePackageName = project.android.defaultConfig.applicationId
        ?: throw IllegalArgumentException("Base package could not be detected.")

    val codeChangeVariables = mapOf(
        "{{packageName}}" to basePackageName,
        "{{featureName}}" to featureName.lowercase(),
        "{{className}}" to featureName.replaceFirstChar { it.titlecase(Locale.getDefault()) }
    )

    val basePackageDir = File(projectDir, "src/main/java/" + basePackageName.replace(".", "/"))
    val featureDir = File(basePackageDir, featureName.lowercase())

    doLast {
        // Check if the feature directory already exists
        require(!featureDir.exists()) { "Feature '$featureName' already exists." }
        featureDir.mkdirs()

        // Template directory
        val templateDir = File(rootDir, "featureTemplate/$templateType")
        check(templateDir.exists() && templateDir.isDirectory) {
            "Template directory '$templateDir' not found or is not a directory."
        }

        // Copy and process template files
        templateDir.walkTopDown().filter { it.isFile }.forEach { file ->
            val featureFileName = "${featureName}${file.name}"
            val targetFile = File(featureDir, "${file.relativeTo(templateDir).parent}/$featureFileName")
            targetFile.parentFile.mkdirs()

            // Read file content and replace placeholders
            var content = file.readText()
            codeChangeVariables.forEach { (key, value) ->
                content = content.replace(key, value)
            }

            targetFile.writeText(content)
            println("Generated file: $targetFile")
        }

        println("Feature '$featureName' generated successfully in $featureDir!")
    }
}
