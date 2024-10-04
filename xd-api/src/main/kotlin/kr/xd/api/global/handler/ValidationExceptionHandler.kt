package kr.xd.api.global.handler

import kr.xd.api.global.dto.ApiErrorResponse
import kr.xd.api.global.util.LoggerUtil
import kr.xd.common.error.GlobalError
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@RestControllerAdvice
class ValidationExceptionHandler(
    private val loggerUtil: LoggerUtil
) {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(
        exception: MethodArgumentNotValidException
    ): ResponseEntity<ApiErrorResponse> {
        val errorEntity = GlobalError.INVALID_PARAMETER
        return handleValidationException(exception, errorEntity)
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleMethodArgumentTypeMismatchException(
        exception: MethodArgumentTypeMismatchException
    ): ResponseEntity<ApiErrorResponse> {
        val errorEntity = GlobalError.INVALID_PARAMETER
        val traceMessage = exception.message
        return handleValidationException(exception, errorEntity)
    }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun handleMethodArgumentTypeMismatchException(
        exception: MissingServletRequestParameterException
    ): ResponseEntity<ApiErrorResponse> {
        val errorEntity = GlobalError.INVALID_PARAMETER
        return handleValidationException(exception, errorEntity)
    }

    private fun handleValidationException(
        exception: Exception,
        errorEntity: GlobalError
    ): ResponseEntity<ApiErrorResponse> {
        loggerUtil.logErrorResponse(exception, errorEntity)

        val errorResponse = ApiErrorResponse(errorEntity)
        return ResponseEntity
            .badRequest()
            .body(errorResponse)
    }
}
