package org.gangneung.ricewinehomepage.implement.business

import org.gangneung.ricewinehomepage.domain.business.Business
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component
import org.thymeleaf.context.Context
import org.thymeleaf.spring6.SpringTemplateEngine

@Component
class BusinessEmailSender(
    private var javaMailSender: JavaMailSender,
    private val templateEngine: SpringTemplateEngine,
) {
    @Value("\${spring.mail.username}")
    private lateinit var email: String

    suspend fun send(business: Business) {
        val context = Context()
        context.setVariable("name", business.name)
        context.setVariable("contact", business.contact)
        context.setVariable("email", business.email)
        context.setVariable("subject", business.subject)
        context.setVariable("content", business.content)

        val message = javaMailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true)
        val content: String = templateEngine.process("business-contact", context)

        helper.setTo(email)
        helper.setSubject(business.subject)
        helper.setText(content, true)
        javaMailSender.send(message)
    }
}
