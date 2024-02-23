package org.gangneung.ricewinehomepage.repository.user

import org.gangneung.ricewinehomepage.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): User?
}
