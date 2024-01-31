package org.gangneung.rice_wine_homepage.repository.coverimage

import org.gangneung.rice_wine_homepage.domain.cover.CoverImage
import org.springframework.data.jpa.repository.JpaRepository

interface CoverImageRepository : JpaRepository<CoverImage, Long> {
}