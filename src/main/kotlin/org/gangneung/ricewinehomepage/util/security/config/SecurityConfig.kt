package org.gangneung.ricewinehomepage.util.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            csrf { disable() }
            formLogin { disable() }
            httpBasic { disable() }
            oauth2Login {
            }
            authorizeHttpRequests {
                authorize("/", permitAll)
                authorize("/login", permitAll)
                authorize(anyRequest, authenticated)
            }
        }
        return http.build()
    }
}
