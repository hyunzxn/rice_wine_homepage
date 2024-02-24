package org.gangneung.ricewinehomepage.implement.review

import org.gangneung.ricewinehomepage.domain.review.Review
import org.gangneung.ricewinehomepage.repository.ReviewRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class ReviewAppender(
    private val reviewRepository: ReviewRepository,
) {
    @Transactional
    fun append(review: Review) {
        reviewRepository.save(review)
    }
}
