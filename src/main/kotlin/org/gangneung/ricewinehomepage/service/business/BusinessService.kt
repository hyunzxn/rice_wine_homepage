package org.gangneung.ricewinehomepage.service.business

import org.gangneung.ricewinehomepage.domain.business.Business
import org.gangneung.ricewinehomepage.implement.business.BusinessAppender
import org.gangneung.ricewinehomepage.implement.business.BusinessEmailSender
import org.springframework.stereotype.Service

@Service
class BusinessService(
    private val businessAppender: BusinessAppender,
    private val businessEmailSender: BusinessEmailSender,
) {
    fun append(business: Business) {
        businessAppender.append(business)
        businessEmailSender.send(business.subject, business.content)
    }
}
