package org.gangneung.ricewinehomepage.util.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity

@EnableMethodSecurity
@Configuration
class MethodSecurityConfig {
    @Bean
    fun methodSecurityExpressionHandler(): MethodSecurityExpressionHandler {
        return DefaultMethodSecurityExpressionHandler()
    }
}
