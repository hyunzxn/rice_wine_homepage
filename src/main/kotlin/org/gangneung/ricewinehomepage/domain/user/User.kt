package org.gangneung.ricewinehomepage.domain.user

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class User private constructor(
    var username: String,
    var name: String,
    var email: String,
    var role: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {
    companion object {
        fun createInstance(
            username: String,
            name: String,
            email: String,
            role: String,
        ): User {
            return User(
                username = username,
                name = name,
                email = email,
                role = role,
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
