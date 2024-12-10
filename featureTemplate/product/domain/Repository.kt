package {{packageName}}.{{featureName}}.domain

import {{packageName}}.{{featureName}}.domain.model.{{className}}

interface {{className}}Repository {
    suspend fun fetch{{className}}(): List<{{className}}>
}
