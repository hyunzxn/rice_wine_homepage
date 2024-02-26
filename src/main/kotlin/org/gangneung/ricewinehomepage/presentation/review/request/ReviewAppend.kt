package org.gangneung.ricewinehomepage.presentation.review.request

import org.gangneung.ricewinehomepage.domain.review.Review
import org.gangneung.ricewinehomepage.domain.user.User
import java.time.LocalDate

data class ReviewAppend(
    val title: String,
    val content: String,
    val grade: Int,
)

fun ReviewAppend.toReview(user: User): Review {
    return Review.createInstance(
        title = this.title,
        content = this.content,
        buyDate = LocalDate.now(),
        grade = this.grade,
        user = user,
    )
}
