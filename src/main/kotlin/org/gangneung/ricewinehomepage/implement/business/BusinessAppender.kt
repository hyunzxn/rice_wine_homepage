package org.gangneung.ricewinehomepage.implement.business

import org.gangneung.ricewinehomepage.domain.business.Business
import org.gangneung.ricewinehomepage.repository.business.BusinessRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class BusinessAppender(
    private val businessRepository: BusinessRepository,
) {
    @Transactional
    fun append(business: Business) {
        businessRepository.save(business)
    }
}
