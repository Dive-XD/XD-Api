package kr.xd.lib.jwt.claims

import kr.xd.lib.jwt.constants.JwtMemberClaims
import java.util.*

sealed interface JwtClaims {
    fun convertToClaims(): EnumMap<JwtMemberClaims, Any?>

    data class UserClaims(
        val id: UUID,
        val nickname: String
    ) : JwtClaims {
        override fun convertToClaims(): EnumMap<JwtMemberClaims, Any?> =
            EnumMap<JwtMemberClaims, Any?>(JwtMemberClaims::class.java).apply {
                put(JwtMemberClaims.ID, id)
                put(JwtMemberClaims.NICKNAME, nickname)
            }
    }
}
