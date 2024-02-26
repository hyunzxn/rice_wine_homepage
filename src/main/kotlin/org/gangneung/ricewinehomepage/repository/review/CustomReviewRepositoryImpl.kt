package org.gangneung.ricewinehomepage.repository.review

import com.querydsl.core.types.Order
import com.querydsl.core.types.OrderSpecifier
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
            .orderBy(*createOrderSpecifier(request.sort))
            .fetch()
    }

    private fun createOrderSpecifier(sortCondition: List<String>): Array<OrderSpecifier<*>> {
        val sortSpecifications = mutableListOf<OrderSpecifier<*>>()

        // sort 조건을 넣지 않으면 기본적으로 최신순부터 정렬
        if (sortCondition.isEmpty()) {
            sortSpecifications.add(OrderSpecifier(Order.DESC, review.id))
        }

        // sort 조건 넣어줌에 따라 정렬
        for (value in sortCondition) {
            if (value == "old") {
                sortSpecifications.add(OrderSpecifier(Order.ASC, review.createdAt))
            }
        }

        return sortSpecifications.toTypedArray()
    }
}
