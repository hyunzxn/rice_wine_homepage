package org.gangneung.ricewinehomepage.implement.review

import org.gangneung.ricewinehomepage.domain.review.Review
import org.gangneung.ricewinehomepage.repository.review.ReviewRepository
import org.gangneung.ricewinehomepage.util.CustomPagingRequest
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class ReviewReader(
    private val reviewRepository: ReviewRepository,
) {
    fun getReviewList(request: CustomPagingRequest): List<Review> {
        return reviewRepository.getReviewList(request)
    }
}
