package org.gangneung.ricewinehomepage.service.review

import org.gangneung.ricewinehomepage.implement.review.ReviewAppender
import org.gangneung.ricewinehomepage.implement.review.ReviewReader
import org.gangneung.ricewinehomepage.implement.user.UserReader
import org.gangneung.ricewinehomepage.presentation.review.request.ReviewAppend
import org.gangneung.ricewinehomepage.presentation.review.request.toReview
import org.gangneung.ricewinehomepage.presentation.review.response.ReviewResponse
import org.gangneung.ricewinehomepage.util.CustomPagingRequest
import org.springframework.stereotype.Service

@Service
class ReviewService(
    private val reviewAppender: ReviewAppender,
    private val reviewReader: ReviewReader,
    private val userReader: UserReader,
) {
    fun append(
        username: String,
        request: ReviewAppend,
    ) {
        val loginUser = userReader.readByUsername(username) ?: throw RuntimeException("$username 회원을 조회할 수 없습니다.")
        val newReview = request.toReview(loginUser)
        reviewAppender.append(newReview)
    }

    fun getReviewList(request: CustomPagingRequest): List<ReviewResponse> {
        val reviewList = reviewReader.getReviewList(request)
        return reviewList.map { ReviewResponse.toResponse(it) }
    }

    fun getReview(id: Long): ReviewResponse {
        val review = reviewReader.readById(id)
        return ReviewResponse.toResponse(review)
    }
}
