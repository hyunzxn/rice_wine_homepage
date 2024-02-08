package org.gangneung.rice_wine_homepage.implement.cover

import org.gangneung.rice_wine_homepage.domain.cover.Cover
import org.gangneung.rice_wine_homepage.repository.cover.CoverRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CoverAppender(
    private val coverRepository: CoverRepository,
) {
    @Transactional
    fun append(cover: Cover): Cover {
        return coverRepository.save(cover)
    }
}