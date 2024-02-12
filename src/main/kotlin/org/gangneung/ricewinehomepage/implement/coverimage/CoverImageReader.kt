package org.gangneung.ricewinehomepage.implement.coverimage

import org.gangneung.ricewinehomepage.domain.cover.CoverImage
import org.gangneung.ricewinehomepage.repository.coverimage.CoverImageRepository
import org.springframework.stereotype.Component

@Component
class CoverImageReader(
    private val coverImageRepository: CoverImageRepository,
) {
    fun readAll(): List<CoverImage> {
        return coverImageRepository.findAll()
    }
}
