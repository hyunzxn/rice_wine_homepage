package org.gangneung.ricewinehomepage.util.security.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.gangneung.ricewinehomepage.service.oauth2.CustomOAuth2UserService
import org.gangneung.ricewinehomepage.util.security.handler.AccessDeniedHandler
import org.gangneung.ricewinehomepage.util.security.handler.UnAuthorizedHandler
import org.gangneung.ricewinehomepage.util.security.jwt.JwtAuthFilter
import org.gangneung.ricewinehomepage.util.security.jwt.JwtUtil
import org.gangneung.ricewinehomepage.util.security.jwt.LoginFilter
import org.gangneung.ricewinehomepage.util.security.oauth2.CustomClientRegistrationRepository
import org.gangneung.ricewinehomepage.util.security.oauth2.handler.CustomSuccessHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    val objectMapper: ObjectMapper,
    val customClientRegistrationRepository: CustomClientRegistrationRepository,
    val customOAuth2UserService: CustomOAuth2UserService,
    val customSuccessHandler: CustomSuccessHandler,
    val jwtUtil: JwtUtil,
    val authenticationConfiguration: AuthenticationConfiguration,
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .formLogin { it.disable() }
            .csrf { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .oauth2Login {
                it.clientRegistrationRepository(customClientRegistrationRepository.clientRegistrationRepository())
                it.userInfoEndpoint { configure ->
                    configure.userService(customOAuth2UserService)
                }
                it.successHandler(customSuccessHandler)
            }
            .authorizeHttpRequests {
                it.anyRequest().permitAll()
            }
            .addFilterAt(
                LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil),
                UsernamePasswordAuthenticationFilter::class.java,
            )
            .addFilterBefore(JwtAuthFilter(jwtUtil), LoginFilter::class.java)
            .addFilterAfter(JwtAuthFilter(jwtUtil), OAuth2LoginAuthenticationFilter::class.java)
            .exceptionHandling {
                it.authenticationEntryPoint(UnAuthorizedHandler(objectMapper))
                it.accessDeniedHandler(AccessDeniedHandler(objectMapper))
            }
        return http.build()
    }

    @Bean
    fun authenticationManager(configuration: AuthenticationConfiguration): AuthenticationManager {
        return configuration.authenticationManager
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
