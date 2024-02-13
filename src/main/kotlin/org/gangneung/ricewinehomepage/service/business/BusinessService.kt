@file:OptIn(DelicateCoroutinesApi::class)

package org.gangneung.ricewinehomepage.service.business

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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
        GlobalScope.launch {
            businessAppender.append(business)
        }
        GlobalScope.launch {
            delay(2_000L)
            businessEmailSender.send(business.subject, business.content)
        }
    }
}
