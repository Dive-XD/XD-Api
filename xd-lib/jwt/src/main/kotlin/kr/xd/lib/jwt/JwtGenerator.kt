package kr.xd.lib.jwt

import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import kr.xd.lib.jwt.constants.JwtProperties
import org.springframework.stereotype.Component
import javax.crypto.SecretKey

@Component
class JwtGenerator(
    private val jwtProperties: JwtProperties
) {
    fun generateKey(): SecretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.key))
}