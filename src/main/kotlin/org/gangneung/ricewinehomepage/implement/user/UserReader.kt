package org.gangneung.ricewinehomepage.implement.user

import org.gangneung.ricewinehomepage.domain.user.User
import org.gangneung.ricewinehomepage.repository.user.UserRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class UserReader(
    private val userRepository: UserRepository,
) {
    fun readByUsername(username: String): User? {
        return userRepository.findByUsername(username)
    }
}
