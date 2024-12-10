package {{packageName}}.{{featureName}}.data.network

import {{packageName}}.{{featureName}}.data.network.dto.{{className}}ResponseDto
import retrofit2.http.GET

interface {{className}}Service {
    @GET("{{featureName}}s")
    suspend fun get{{className}}(): List<{{className}}ResponseDto>
}
