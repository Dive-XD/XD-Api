package kr.xd.lib.jwt.error

import kr.xd.common.error.ErrorEntity

enum class AuthenticationError(
    override val message: String,
    override val httpStatus: Int,
    override val code: String,
) : ErrorEntity {
    INVALID_JWT_SIGNATURE("JWT 토큰 시그니처가 유효하지 않습니다.", 403, "ATH_001"),
    INVALID_JWT_TOKEN("토큰이 유효하지 않습니다.", 403, "ATH_002"),
    EXPIRED_JWT_TOKEN("토큰이 만료되었습니다.", 403, "ATH_003"),
    UNSUPPORTED_JWT_TOKEN("지원되지 않는 토큰입니다.", 403, "ATH_004"),
    UNSUPPORTED_PROVIDER("지원하지 않는 로그인 클라이언트입니다.", 400, "ATH_005"),
    UNDEFINED_TOKEN_ERROR("정의되지 않은 JWT 로그인 오류가 발생했습니다.", 400, "ATH_006"),
    UNKNOWN_CLAIM_CONSTANTS("유효하지 않은 JWT Claim이 요청되었습니다.", 400, "ATH_007"),
    INVALID_USER_ID("요청된 토큰으로 유저를 찾을 수 없습니다.", 400, "ATH_008"),

    // HttpStatus Response Rule
    ERR_UNAUTHORIZED("해당 리소스에 접근할 권한이 없습니다.", 401, "ATH_401"),
    ERR_FORBIDDEN("요청이 제한된 리소스입니다.", 403, "ATH_403")
}