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

    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.coil.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

tasks.register("moveTemplateToFeature") {
    val rootFolderName = "featureTemplate"

    val featureName = project.findProperty("featureName") as String?
        ?: error("Feature name is required. Use -PfeatureName=<name> to specify it.")

    val templateName = project.findProperty("templateName") as String?
        ?: error("Template name is required. Use -PtemplateName=<name> to specify it.")

    // Retrieve the base package name from the project configuration
    val basePackageName = project.android.defaultConfig.applicationId
        ?: error("Base package could not be detected. Ensure applicationId is set in defaultConfig.")

    // Construct the base output path for the generated files
    val generatedFilePath = File(projectDir, "src/main/java/${basePackageName.replace(".", "/")}")

    // Map of placeholders to be replaced in template files
    val placeholders = mapOf(
        "{{packageName}}" to basePackageName,
        "{{featureName}}" to featureName.lowercase(),
        "{{className}}" to featureName.replaceFirstChar { it.titlecase(Locale.getDefault()) }
    )

    doLast {
        val featureDir = File(generatedFilePath, featureName.lowercase())
        if (featureDir.exists()) error("Feature '$featureName' already exists at ${featureDir.path}.")
        featureDir.mkdirs()

        val templateDir = File(rootDir, "$rootFolderName/$templateName")
        check(templateDir.exists() && templateDir.isDirectory) {
            "Template directory '$templateDir' not found or is not a directory."
        }

        // Copy and customize template files
        templateDir.walkTopDown()
            .filter { it.isFile }
            .forEach { file ->
                val relativePath = file.relativeTo(templateDir).parent ?: ""
                val featureFileName = "${featureName}${file.name}"
                val targetFile = File(featureDir, "$relativePath/$featureFileName")
                targetFile.parentFile.mkdirs()

                // Read file content and replace placeholders
                var content = file.readText()
                placeholders.forEach { (key, value) ->
                    content = content.replace(key, value)
                }
                targetFile.writeText(content)
                println("Generated file: ${targetFile.path}")
            }

        println("Feature '$featureName' successfully generated at ${featureDir.path}.")
    }
}