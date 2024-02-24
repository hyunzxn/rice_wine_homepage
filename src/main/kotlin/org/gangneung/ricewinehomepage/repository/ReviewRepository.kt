package org.gangneung.ricewinehomepage.repository

import org.gangneung.ricewinehomepage.domain.review.Review
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewRepository : JpaRepository<Review, Long>
