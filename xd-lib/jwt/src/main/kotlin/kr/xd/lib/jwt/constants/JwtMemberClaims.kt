package kr.xd.lib.jwt.constants

enum class JwtMemberClaims(
    val value: String
) {
    ID("id"),
    NICKNAME("nickname")
}