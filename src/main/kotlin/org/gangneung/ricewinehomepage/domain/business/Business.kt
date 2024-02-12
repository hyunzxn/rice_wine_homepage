package org.gangneung.ricewinehomepage.domain.business

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Lob

@Entity
class Business private constructor(
    @Column(nullable = false)
    val name: String,
    @Column(nullable = false)
    val contact: String,
    @Column(nullable = false)
    val email: String,
    @Lob
    @Column(nullable = false)
    val content: String,
    @Column(nullable = false)
    val subject: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {
    companion object {
        fun createInstance(
            name: String,
            contact: String,
            email: String,
            subject: String,
            content: String,
        ): Business {
            return Business(
                name = name,
                contact = contact,
                email = email,
                subject = subject,
                content = content,
            )
        }
    }
}
