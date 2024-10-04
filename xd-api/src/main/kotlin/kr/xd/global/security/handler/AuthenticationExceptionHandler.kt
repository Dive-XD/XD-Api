package kr.xd.global.security.handler

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletResponse
import kr.xd.common.error.ErrorEntity
import kr.xd.common.error.ErrorResponse
import kr.xd.error.AuthenticationError
import kr.xd.global.util.LoggerUtil
import org.springframework.http.MediaType
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.nio.charset.StandardCharsets

@RestControllerAdvice
class AuthenticationExceptionHandler(
    private val objectMapper: ObjectMapper,
    private val loggerUtil: LoggerUtil
) {

    fun unauthorizedHandler(): AuthenticationEntryPoint {
        return AuthenticationEntryPoint { _, response, exception ->
            val errorEntity = AuthenticationError.ERR_UNAUTHORIZED

            loggerUtil.logErrorResponse(exception, errorEntity)
            writeErrorResponse(response, errorEntity)
        }
    }

    fun forbiddenHandler(): AccessDeniedHandler {
        return AccessDeniedHandler { _, response, exception ->
            val errorEntity = AuthenticationError.ERR_FORBIDDEN
            val traceMessage = exception.message

            loggerUtil.logErrorResponse(exception, errorEntity)
            writeErrorResponse(response, errorEntity)
        }
    }

    private fun writeErrorResponse(
        response: HttpServletResponse,
        errorEntity: ErrorEntity,
    ) {
        val errorResponse = ErrorResponse(errorEntity)

        response.status = errorEntity.httpStatus
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = StandardCharsets.UTF_8.name()

        response.writer.use { it.write(objectMapper.writeValueAsString(errorResponse)) }
    }
}
