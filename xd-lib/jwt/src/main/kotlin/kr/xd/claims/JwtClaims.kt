package kr.xd.claims

import kr.xd.constants.JwtMemberClaims
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
