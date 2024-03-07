package org.gangneung.ricewinehomepage.util.security.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.gangneung.ricewinehomepage.service.auth.CustomUserDetails
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class LoginFilter(
    private val authenticationManager: AuthenticationManager,
    private val jwtUtil: JwtUtil,
) : UsernamePasswordAuthenticationFilter() {
    override fun attemptAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
    ): Authentication {
        val username: String? = obtainUsername(request)
        val password: String? = obtainPassword(request)

        val authToken = UsernamePasswordAuthenticationToken(username, password, null)

        return authenticationManager.authenticate(authToken)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authentication: Authentication,
    ) {
        val customUserDetails: CustomUserDetails = authentication.principal as CustomUserDetails
        val username = customUserDetails.username

        val authorities: Collection<GrantedAuthority> = authentication.authorities
        val iterator: Iterator<GrantedAuthority> = authorities.iterator()
        val auth: GrantedAuthority = iterator.next()

        val role: String = auth.authority

        val token = jwtUtil.createJwt(username, role, "password", 60 * 10000L)

        val cookie = Cookie("Authorization", token)
        response.addCookie(cookie)
    }

    override fun unsuccessfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        failed: AuthenticationException,
    ) {
        response.status = 401
        println("왜 로그인이 안 되지?")
    }
}
