package org.gangneung.rice_wine_homepage.implement.coverimage

import org.gangneung.rice_wine_homepage.domain.cover.CoverImage
import org.gangneung.rice_wine_homepage.repository.coverimage.CoverImageRepository
import org.springframework.stereotype.Component

@Component
class CoverImageReader(
    private val coverImageRepository: CoverImageRepository,
) {
    fun readAll(): List<CoverImage> {
        return coverImageRepository.findAll()
    }
}