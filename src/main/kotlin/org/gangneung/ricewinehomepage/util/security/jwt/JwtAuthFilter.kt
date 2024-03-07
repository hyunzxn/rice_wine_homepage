package org.gangneung.ricewinehomepage.util.security.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.gangneung.ricewinehomepage.domain.user.User
import org.gangneung.ricewinehomepage.service.auth.CustomUserDetails
import org.gangneung.ricewinehomepage.util.security.oauth2.CustomOAuth2User
import org.gangneung.ricewinehomepage.util.security.oauth2.UserDto
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthFilter(
    private val jwtUtil: JwtUtil,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        var authorization: String? = null
        val cookies = request.cookies
        cookies?.forEach { cookie ->
            if (cookie.name == "Authorization") {
                authorization = cookie.value
            }
        } ?: return

        // 쿠키에 JWT 값이 담겨있지 않은 경우 강제 종료
        if (authorization == null) {
            filterChain.doFilter(request, response)
            return
        }

        val token = authorization

        // 토큰의 유효 시간이 만료된 경우 강제 종료
        if (jwtUtil.isExpired(token ?: return)) {
            filterChain.doFilter(request, response)
            return
        }

        val username = jwtUtil.getUsername(token)
        val role = jwtUtil.getRole(token)
        val type = jwtUtil.getType(token)

        val userDto = UserDto(username, "user", role)
        if (type == "social") {
            val customOAuth2User = CustomOAuth2User(userDto)
            val emptyContext = SecurityContextHolder.createEmptyContext()
            SecurityContextHolder.setContext(emptyContext)
            val authToken =
                UsernamePasswordAuthenticationToken(customOAuth2User, null, customOAuth2User.authorities)
            SecurityContextHolder.getContext().authentication = authToken
            filterChain.doFilter(request, response)
        } else {
            val user =
                User.createInstance(
                    username = username,
                    name = "user",
                    email = "email",
                    role = role,
                )
            val customUserDetails = CustomUserDetails(user)
            val emptyContext = SecurityContextHolder.createEmptyContext()
            SecurityContextHolder.setContext(emptyContext)
            val authToken =
                UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.authorities)
            SecurityContextHolder.getContext().authentication = authToken
            filterChain.doFilter(request, response)
        }
    }
}
