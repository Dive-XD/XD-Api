package kr.xd

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import kr.xd.common.exception.BusinessException
import kr.xd.constants.JwtClaimConstants
import kr.xd.constants.JwtProperties
import kr.xd.error.AuthenticationError
import org.springframework.stereotype.Component

@Component
class JwtValidator(
    private val jwtGenerator: JwtGenerator,
    private val jwtProperties: JwtProperties
) {
    fun getUserClaim(
        token: String,
        jwtClaimConstants: JwtClaimConstants
    ): Any? {
        val tokenPayload = validateToken(token)
        return tokenPayload[jwtClaimConstants.value]
    }

    private fun validateToken(token: String) =
        try {
            Jwts.parser()
                .verifyWith(jwtGenerator.generateKey())
                .build()
                .parseSignedClaims(token)
                .payload
        } catch (exception: Exception) {
            when (exception) {
                is SecurityException
                    -> throw BusinessException(AuthenticationError.INVALID_JWT_SIGNATURE)

                is MalformedJwtException,
                is ExpiredJwtException
                    -> throw BusinessException(AuthenticationError.INVALID_JWT_TOKEN)

                is UnsupportedJwtException,
                is IllegalArgumentException
                    -> throw BusinessException(AuthenticationError.UNSUPPORTED_JWT_TOKEN)

                else
                    -> throw BusinessException(AuthenticationError.UNDEFINED_TOKEN_ERROR)
            }
        }
}
