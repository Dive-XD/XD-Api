package kr.xd.lib.jwt.constants

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jwt")
class JwtProperties(
    val key: String,
    val validity: Long
)