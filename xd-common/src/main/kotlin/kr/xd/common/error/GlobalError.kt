package kr.xd.common.error

enum class GlobalError(
    override val message: String,
    override val httpStatus: Int,
    override val code: String
) : ErrorEntity {
    INVALID_PARAMETER("요청 파라미터가 유효하지 않습니다.", 400, "GLB_001"),


    GLOBAL_NOT_FOUND("해당 리소스를 찾을 수 없습니다.", 404, "GLB_404"),
    INTERNAL_SERVER_ERROR("서버에 오류가 발생했습니다. 잠시 후 다시 시도하세요.", 500, "GLB_500"),
}
