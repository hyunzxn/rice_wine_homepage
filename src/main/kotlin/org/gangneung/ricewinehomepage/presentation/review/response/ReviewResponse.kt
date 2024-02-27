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
    val viewCount: Int,
) {
    companion object {
        fun toResponse(review: Review): ReviewResponse {
            val name = review.user.name
            val maskingName = name[0] + "*".repeat(name.length - 1)
            return ReviewResponse(
                id = review.id!!,
                username = maskingName,
                date = review.buyDate,
                title = review.title,
                content = review.content,
                grade = review.grade,
                viewCount = review.viewCount,
            )
        }
    }
}
