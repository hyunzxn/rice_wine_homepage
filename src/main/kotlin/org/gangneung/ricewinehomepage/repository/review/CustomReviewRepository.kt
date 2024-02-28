package org.gangneung.ricewinehomepage.repository.review

import org.gangneung.ricewinehomepage.domain.review.Review
import org.gangneung.ricewinehomepage.util.CustomPagingRequest

interface CustomReviewRepository {
    fun getReviewList(request: CustomPagingRequest): List<Review>

    fun search(q: String): List<Review>
}
