package org.gangneung.ricewinehomepage.implement.review

import org.gangneung.ricewinehomepage.domain.review.Review
import org.gangneung.ricewinehomepage.domain.review.ReviewLike
import org.gangneung.ricewinehomepage.domain.user.User
import org.gangneung.ricewinehomepage.repository.review.ReviewLikeRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class ReviewLikeAppender(
    private val reviewLikeRepository: ReviewLikeRepository,
    private val reviewLikeReader: ReviewLikeReader,
    private val reviewAppender: ReviewAppender,
) {
    @Transactional
    fun append(
        review: Review,
        user: User,
    ) {
        val existLike = reviewLikeReader.read(review, user)
        if (existLike != null) {
            review.cancelLike()
            reviewAppender.append(review)
            reviewLikeRepository.delete(existLike)
        } else {
            review.addLike()
            reviewAppender.append(review)
            reviewLikeRepository.save(ReviewLike.createInstance(review, user))
        }
    }
}
