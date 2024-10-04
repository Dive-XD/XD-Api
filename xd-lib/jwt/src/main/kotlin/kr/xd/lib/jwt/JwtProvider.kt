package kr.xd.lib.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import kr.xd.lib.jwt.claims.JwtClaims
import kr.xd.lib.jwt.constants.JwtClaimConstants.USER_CLAIM
import kr.xd.lib.jwt.constants.JwtProperties
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtProvider(
    private val jwtProperties: JwtProperties
) {
    private val secretKey: SecretKey = Keys.hmacShaKeyFor(jwtProperties.key.toByteArray())

    fun issueToken(jwtClaims: JwtClaims): String {
        val now = Date()
        val expiration = Date(now.time + jwtProperties.validity)

        return Jwts.builder()
            .issuedAt(now)
            .claim(USER_CLAIM.value, jwtClaims.convertToClaims())
            .signWith(secretKey)
            .expiration(expiration)
            .compact()
    }
}