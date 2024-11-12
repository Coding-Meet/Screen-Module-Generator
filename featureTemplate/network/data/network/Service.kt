package {{packageName}}.{{featureName}}.data.network

import {{packageName}}.{{featureName}}.data.network.dto.{{className}}ResponseDto
import retrofit2.http.GET

interface {{className}}Service {
    @GET("products")
    suspend fun get{{className}}(): List<{{className}}ResponseDto>
}
