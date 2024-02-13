package org.gangneung.ricewinehomepage.implement.business

import org.gangneung.ricewinehomepage.util.logger
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component

@Component
class BusinessEmailSender(
    private var javaMailSender: JavaMailSender,
) {
    private val log = logger()

    @Value("\${spring.mail.username}")
    private lateinit var email: String

    suspend fun send(
        subject: String,
        content: String,
    ) {
        val message = SimpleMailMessage()
        message.from = "noreply@baeldung.com"
        message.setTo(email) // todo 추후 가게 주인 Email로 수정 필요
        message.subject = subject
        message.text = content
        log.info("메일 보내는 것에 성공했습니다.")
        javaMailSender.send(message)
    }
}
