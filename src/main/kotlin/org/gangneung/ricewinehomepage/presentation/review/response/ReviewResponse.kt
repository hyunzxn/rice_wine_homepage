package org.gangneung.ricewinehomepage.presentation.review.response

import org.gangneung.ricewinehomepage.domain.review.Review
import java.time.LocalDate

data class ReviewResponse(
    val id: Long,
    val username: String,
    val date: LocalDate,
    val title: String,
    val content: String,
    val grade: Int,
) {
    companion object {
        fun toResponse(review: Review): ReviewResponse {
            return ReviewResponse(
                id = review.id!!,
                username = review.user.name,
                date = review.buyDate,
                title = review.title,
                content = review.content,
                grade = review.grade,
            )
        }
    }
}
