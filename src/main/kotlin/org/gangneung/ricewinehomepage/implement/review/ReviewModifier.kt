package org.gangneung.ricewinehomepage.implement.review

import org.gangneung.ricewinehomepage.domain.review.Review
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class ReviewModifier(
    private val reviewAppender: ReviewAppender,
) {
    @Transactional
    fun updateViewCount(review: Review) {
        review.updateViewCount()
        reviewAppender.append(review)
    }
}
