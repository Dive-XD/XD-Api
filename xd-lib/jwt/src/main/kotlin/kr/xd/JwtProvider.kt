package kr.xd

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import kr.xd.claims.JwtClaims
import kr.xd.constants.JwtClaimConstants
import kr.xd.constants.JwtProperties
import java.util.*
import javax.crypto.SecretKey

class JwtProvider(
    private val jwtProperties: JwtProperties
) {
    private val secretKey: SecretKey = Keys.hmacShaKeyFor(jwtProperties.key.toByteArray())

    fun issueToken(jwtClaims: JwtClaims): String {
        val now = Date()
        val expiration = Date(now.time + jwtProperties.validity)

        return Jwts.builder()
            .issuedAt(now)
            .claim(JwtClaimConstants.USER_CLAIM, jwtClaims.convertToClaims())
            .signWith(secretKey)
            .expiration(expiration)
            .compact()
    }
}