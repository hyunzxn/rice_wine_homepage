package org.gangneung.ricewinehomepage.presentation.auth.request

import org.gangneung.ricewinehomepage.domain.user.User

data class Signup(
    val username: String,
    val password: String,
    val name: String,
    val email: String,
) {
    fun validate() {
        if (username.isBlank() || password.isBlank() || name.isBlank() || email.isBlank()) {
            throw IllegalArgumentException("아이디, 비밀번호, 이름 또는 이메일을 다시 확인해주세요.")
        }
    }

    fun toUser(encryptPassword: String): User {
        return User.createInstance(
            username = username,
            password = encryptPassword,
            name = name,
            email = email,
            role = "ROLE_USER",
        )
    }
}
