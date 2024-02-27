package org.gangneung.ricewinehomepage.repository.review

import org.gangneung.ricewinehomepage.domain.review.Review
import org.gangneung.ricewinehomepage.domain.review.ReviewLike
import org.gangneung.ricewinehomepage.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewLikeRepository : JpaRepository<ReviewLike, Long> {
    fun findByReviewAndUser(
        review: Review,
        user: User,
    ): ReviewLike?
}
