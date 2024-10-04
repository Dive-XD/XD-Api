package kr.xd.global.entity

enum class GlobalError(
    override val message: String,
    override val httpStatus: Int,
    override val code: String
) : ErrorEntity {
    GLOBAL_NOT_FOUND("해당 리소스를 찾을 수 없습니다.", 404, "GLB_001"),
    INVALID_PARAMETER("요청 파라미터가 유효하지 않습니다.", 400, "GLB_002"),
}
