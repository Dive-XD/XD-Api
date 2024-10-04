package kr.xd.api.global.util

import jakarta.servlet.http.HttpServletRequest
import kr.xd.api.global.util.LoggerConstants.REQUEST
import kr.xd.api.global.util.LoggerConstants.STACKTRACE
import kr.xd.common.error.ErrorEntity
import org.slf4j.Logger
import org.springframework.stereotype.Component

@Component
class LoggerUtil(
    private val log: Logger
) {
    fun logClientRequest(
        request: HttpServletRequest
    ) {
        log.info(REQUEST, request.method, request.requestURI, request.remoteAddr)
    }

    fun logErrorResponse(
        exception: Exception,
        error: ErrorEntity
    ) {
        log.error(LoggerConstants.TRACE, error.code, error.httpStatus, error.message)
        log.error(STACKTRACE, exception.message)
    }
}

private object LoggerConstants {
    const val REQUEST = "REQ | {}: {} ({})"
    const val TRACE = "ERR | {} ({}) - {}"
    const val TRACE_WITH_MSG = "$TRACE | {}"
    const val STACKTRACE = "--> ERR TRACE | {}"
}