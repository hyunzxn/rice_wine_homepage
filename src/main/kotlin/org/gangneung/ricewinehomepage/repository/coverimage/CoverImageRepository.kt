package org.gangneung.ricewinehomepage.repository.coverimage

import org.gangneung.ricewinehomepage.domain.cover.CoverImage
import org.springframework.data.jpa.repository.JpaRepository

interface CoverImageRepository : JpaRepository<CoverImage, Long>
