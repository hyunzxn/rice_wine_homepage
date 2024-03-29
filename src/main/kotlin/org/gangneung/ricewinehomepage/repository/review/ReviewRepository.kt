package org.gangneung.ricewinehomepage.repository.review

import org.gangneung.ricewinehomepage.domain.review.Review
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewRepository : JpaRepository<Review, Long>, CustomReviewRepository
