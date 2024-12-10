package {{packageName}}.{{featureName}}.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class {{className}}ResponseDto(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String
)
