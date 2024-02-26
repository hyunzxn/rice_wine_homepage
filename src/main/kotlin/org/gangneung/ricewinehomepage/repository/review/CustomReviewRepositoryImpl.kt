package org.gangneung.ricewinehomepage.repository.review

import com.querydsl.jpa.impl.JPAQueryFactory
import org.gangneung.ricewinehomepage.domain.review.QReview.review
import org.gangneung.ricewinehomepage.domain.review.Review
import org.gangneung.ricewinehomepage.util.CustomPagingRequest

class CustomReviewRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory,
) : CustomReviewRepository {
    override fun getReviewList(request: CustomPagingRequest): List<Review> {
        return jpaQueryFactory
            .selectFrom(review)
            .offset(request.offset)
            .limit(request.size.toLong())
            .orderBy(review.id.desc())
            .fetch()
    }
}
