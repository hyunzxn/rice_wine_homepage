package org.gangneung.ricewinehomepage.implement.review

import org.gangneung.ricewinehomepage.domain.review.Review
import org.gangneung.ricewinehomepage.domain.review.ReviewLike
import org.gangneung.ricewinehomepage.domain.user.User
import org.gangneung.ricewinehomepage.repository.review.ReviewLikeRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class ReviewLikeReader(
    private val reviewLikeRepository: ReviewLikeRepository,
) {
    fun read(
        review: Review,
        user: User,
    ): ReviewLike? {
        return reviewLikeRepository.findByReviewAndUser(review, user)
    }
}
