package org.gangneung.ricewinehomepage.service.auth

import org.gangneung.ricewinehomepage.implement.user.UserAppender
import org.gangneung.ricewinehomepage.implement.user.UserReader
import org.gangneung.ricewinehomepage.presentation.auth.request.Signup
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userAppender: UserAppender,
    private val userReader: UserReader,
    private val passwordEncoder: PasswordEncoder,
) {
    fun signup(request: Signup) {
        val username = request.username
        val password = request.password
        val newUser = request.toUser(passwordEncoder.encode(password))
        userReader.readByUsername(username)?.let {
            throw RuntimeException("${username}은 이미 존재하는 아이디입니다.")
        } ?: userAppender.append(newUser)
    }
}
