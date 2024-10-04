package kr.xd.api.global.handler

import kr.xd.api.global.dto.ApiErrorResponse
import kr.xd.api.global.util.LoggerUtil
import kr.xd.common.error.GlobalError
import kr.xd.common.exception.BusinessException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.resource.NoResourceFoundException

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

    @ExceptionHandler(NoResourceFoundException::class)
    fun handleUnexpectedException(
        exception: NoResourceFoundException
    ): ResponseEntity<ApiErrorResponse> {
        val errorEntity = GlobalError.GLOBAL_NOT_FOUND
        loggerUtil.logErrorResponse(exception, errorEntity)

        return ResponseEntity
            .status(errorEntity.httpStatus)
            .body(ApiErrorResponse(errorEntity))
    }

    @ExceptionHandler(Exception::class)
    fun handleUnexpectedException(
        exception: Exception
    ): ResponseEntity<ApiErrorResponse> {
        val errorEntity = GlobalError.INTERNAL_SERVER_ERROR
        loggerUtil.logErrorResponse(exception, errorEntity)

        return ResponseEntity
            .status(errorEntity.httpStatus)
            .body(ApiErrorResponse(errorEntity))
    }
}