package org.gangneung.ricewinehomepage.domain.review

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.gangneung.ricewinehomepage.domain.user.User

@Entity
class ReviewLike private constructor(
    @ManyToOne
    @JoinColumn(name = "review_id")
    val review: Review,
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {
    companion object {
        fun createInstance(
            review: Review,
            user: User,
        ): ReviewLike {
            return ReviewLike(
                review = review,
                user = user,
            )
        }
    }
}
