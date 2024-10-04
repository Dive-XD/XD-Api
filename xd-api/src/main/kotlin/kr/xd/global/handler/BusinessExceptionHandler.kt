package kr.xd.global.handler

import kr.xd.global.dto.ApiErrorResponse
import kr.xd.global.exception.BusinessException
import kr.xd.global.util.LoggerUtil
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class BusinessExceptionHandler(
    private val loggerUtil: LoggerUtil
) {

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(
        exception: BusinessException
    ): ResponseEntity<ApiErrorResponse> {
        val errorEntity = exception.errorEntity
        loggerUtil.logErrorResponse(exception, errorEntity)

        return ResponseEntity
            .status(errorEntity.httpStatus)
            .body(ApiErrorResponse(errorEntity))
    }
}