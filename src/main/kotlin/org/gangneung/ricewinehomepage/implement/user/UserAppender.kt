package org.gangneung.ricewinehomepage.implement.user

import org.gangneung.ricewinehomepage.domain.user.User
import org.gangneung.ricewinehomepage.repository.user.UserRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class UserAppender(
    private val userRepository: UserRepository,
) {
    @Transactional
    fun append(user: User) {
        userRepository.save(user)
    }
}
