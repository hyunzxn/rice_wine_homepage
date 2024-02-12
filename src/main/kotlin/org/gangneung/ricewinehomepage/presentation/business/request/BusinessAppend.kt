package org.gangneung.ricewinehomepage.presentation.business.request

import org.gangneung.ricewinehomepage.domain.business.Business

data class BusinessAppend(
    val name: String,
    val contact: String,
    val email: String,
    val subject: String,
    val content: String,
) {
    fun toBusiness(): Business {
        if (name.isBlank() || contact.isBlank() || email.isBlank() || subject.isBlank() || content.isBlank()) {
            throw IllegalArgumentException("이름, 연락처, 이메일, 문의 내용은 공백일 수 없습니다.")
        }
        return Business.createInstance(
            name = name,
            contact = contact,
            email = email,
            subject = subject,
            content = content,
        )
    }
}
