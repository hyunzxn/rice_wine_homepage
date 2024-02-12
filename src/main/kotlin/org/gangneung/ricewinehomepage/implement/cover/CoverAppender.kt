package org.gangneung.ricewinehomepage.implement.cover

import org.gangneung.ricewinehomepage.domain.cover.Cover
import org.gangneung.ricewinehomepage.repository.cover.CoverRepository
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
