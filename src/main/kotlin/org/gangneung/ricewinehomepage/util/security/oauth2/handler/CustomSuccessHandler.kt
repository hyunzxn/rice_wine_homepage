package org.gangneung.ricewinehomepage.util.security.oauth2.handler

import jakarta.servlet.ServletException
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.gangneung.ricewinehomepage.util.security.jwt.JwtUtil
import org.gangneung.ricewinehomepage.util.security.oauth2.CustomOAuth2User
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.stereotype.Component
import java.io.IOException

@Component
class CustomSuccessHandler(
    private val jwtUtil: JwtUtil,
) : SimpleUrlAuthenticationSuccessHandler() {
    @Throws(IOException::class, ServletException::class)
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication,
    ) {
        val customUserDetails = authentication.principal as CustomOAuth2User
        val username = customUserDetails.getUsername()
        val authorities = authentication.authorities
        val role = authorities.first().authority
        val token = jwtUtil.createJwt(username, role, "social", 60 * 10000L) // todo 개발 환경에선 10분 => 추후 변경 필요

        response.addCookie(createCookie("Authorization", token))
    }

    private fun createCookie(
        key: String,
        value: String,
    ): Cookie {
        return Cookie(key, value).apply {
            maxAge = 60 * 60 * 60
            path = "/"
            isHttpOnly = true
        }
    }
}
