package kr.xd.global.util

import kr.xd.global.entity.ErrorEntity
import org.slf4j.Logger
import org.springframework.stereotype.Component

@Component
class LoggerUtil(
    private val log: Logger
) {
    fun logErrorResponse(
        exception: Exception,
        error: ErrorEntity,
        traceMessage: String? = null
    ) {
        when (traceMessage) {
            null -> log.error(TRACE, error.code, error.httpStatus, error.message)
            else -> log.error(TRACE_WITH_MSG, error.code, error.httpStatus, error.message, traceMessage)
        }
    }

    private companion object {
        const val REQUEST = "REQ | {}: {} ({}) - {}"
        const val TRACE = "ERR | {} ({}) - {}"
        const val TRACE_WITH_MSG = "$TRACE | {}"
    }
}