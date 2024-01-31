package org.gangneung.rice_wine_homepage.repository.cover

import org.gangneung.rice_wine_homepage.domain.cover.Cover
import org.springframework.data.jpa.repository.JpaRepository

interface CoverRepository : JpaRepository<Cover, Long> {
}