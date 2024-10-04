package kr.xd.global.security.config

import kr.xd.JwtProvider
import kr.xd.global.security.handler.AuthenticationExceptionHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtProvider: JwtProvider,
    private val authenticationExceptionHandler: AuthenticationExceptionHandler
) {
    @Bean
    fun authFilterChain(
        httpSecurity: HttpSecurity
    ): SecurityFilterChain =
        httpSecurity
            .also(::disableDefaultSecurityConfiguration)
            .also(::enableCoreSecurityConfiguration)
            .also(::applyHttpRequestMatchers)
            .build()


    @Bean
    fun userDetailsService(): UserDetailsService = InMemoryUserDetailsManager()

    private fun disableDefaultSecurityConfiguration(
        http: HttpSecurity
    ): HttpSecurity =
        http
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .logout { it.disable() }
            .httpBasic { it.disable() }

    private fun enableCoreSecurityConfiguration(
        http: HttpSecurity
    ): HttpSecurity =
        http
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .exceptionHandling {
                it.accessDeniedHandler(authenticationExceptionHandler.forbiddenHandler())
                it.authenticationEntryPoint(authenticationExceptionHandler.unauthorizedHandler())
            }

    private fun applyHttpRequestMatchers(
        http: HttpSecurity
    ): HttpSecurity =
        http.authorizeHttpRequests {
            it.requestMatchers(HttpMethod.GET, "/").anonymous()
            it.anyRequest().permitAll()
        }
}
