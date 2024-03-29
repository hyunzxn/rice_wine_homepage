package org.gangneung.ricewinehomepage.domain.user

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

/**
 * username => {서비스명}-{고유아이디} / 자체 로그인 시에는 유저가 입력한 아이디
 *
 * name => 네이버 이름 또는 구글 닉네임
 */
@Entity
class User private constructor(
    var username: String,
    var password: String?,
    var name: String,
    var email: String,
    var role: String,
    val loginProvider: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {
    companion object {
        fun createInstance(
            username: String,
            password: String? = null,
            name: String,
            email: String,
            role: String,
            loginProvider: String = "GENERAL",
        ): User {
            return User(
                username = username,
                password = password,
                name = name,
                email = email,
                role = role,
                loginProvider = loginProvider,
            )
        }
    }

    fun update(
        name: String,
        email: String,
    ) {
        this.name = name
        this.email = email
    }
}
