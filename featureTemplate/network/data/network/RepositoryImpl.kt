package {{packageName}}.{{featureName}}.data.network

import {{packageName}}.{{featureName}}.data.mappers.toDomainModel
import {{packageName}}.{{featureName}}.domain.{{className}}Repository
import {{packageName}}.{{featureName}}.domain.model.{{className}}


class {{className}}RepositoryImpl(private val api: {{className}}Service) : {{className}}Repository {
    override suspend fun fetch{{className}}(): List<{{className}}> {
        val response = api.get{{className}}() // Assume this method fetches data from API
        return response.map { it.toDomainModel() }
    }
}
