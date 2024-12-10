package {{packageName}}.{{featureName}}.data.mappers

import {{packageName}}.{{featureName}}.data.network.dto.{{className}}ResponseDto
import {{packageName}}.{{featureName}}.domain.model.*

fun {{className}}ResponseDto.toDomainModel(): {{className}} {
    return {{className}}(
        id = this.id,
        title = this.title,
        price = this.price,
        description = this.description,
        category = this.category,
        image = this.image
    )
}
