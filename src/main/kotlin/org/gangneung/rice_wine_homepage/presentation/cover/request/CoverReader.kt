package org.gangneung.rice_wine_homepage.presentation.cover.request

import org.gangneung.rice_wine_homepage.domain.cover.Cover
import org.gangneung.rice_wine_homepage.repository.cover.CoverRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class CoverReader(
    private val coverRepository: CoverRepository,
) {
    fun readBy(id: Long): Cover {
        return coverRepository.findByIdOrNull(id) ?: throw RuntimeException("존재하지 않는 소개글입니다.")
    }
}