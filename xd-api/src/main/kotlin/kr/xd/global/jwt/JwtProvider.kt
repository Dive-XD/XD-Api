package kr.xd.global.jwt

import org.springframework.beans.factory.annotation.Value

class JwtProvider(
    @Value("\${jwt.key}")
    private val encodedSecretKey: String,

    @Value("\${jwt.validity.access-token}")
    private val accessTokenValidity: Long
)
//    private val secretKey: SecretKey = Keys.hmacShaKeyFor(encodedSecretKey.toByteArray())
//
//    fun issueToken(jwtType: JwtType): String {
//        val now = Date()
//        val expiration = Date(now.time + accessTokenValidity)
//
//        return Jwts.builder()
//            .issuedAt(now)
//            .claim(USER_CLAIM, JwtClaims.UserClaims(role = GrantedAuthorityRole.USER))
//            .signWith(secretKey)
//            .expiration(expiration)
//            .compact()
//    }