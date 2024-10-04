package kr.xd.common.error

import java.time.LocalDateTime

data class ErrorResponse(
    val timeStamp: String = LocalDateTime.now().toString(),
    val errorCode: String,
    val errorMessage: String?,
) {
    constructor(errorEntity: ErrorEntity) : this(
        errorCode = errorEntity.code,
        errorMessage = errorEntity.message
    )
}