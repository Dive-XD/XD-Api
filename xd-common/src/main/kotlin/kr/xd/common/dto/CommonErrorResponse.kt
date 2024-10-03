package kr.xd.common.dto

import kr.xd.common.entity.ErrorEntity
import java.time.LocalDateTime

data class CommonErrorResponse(
    val timeStamp: String = LocalDateTime.now().toString(),
    val errorCode: String,
    val errorMessage: String?,
) {
    constructor(errorEntity: ErrorEntity) : this(
        errorCode = errorEntity.code,
        errorMessage = errorEntity.message
    )
}
