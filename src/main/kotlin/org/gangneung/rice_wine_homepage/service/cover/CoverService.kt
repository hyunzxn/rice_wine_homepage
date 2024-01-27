package org.gangneung.rice_wine_homepage.service.cover

import org.gangneung.rice_wine_homepage.domain.cover.Cover
import org.gangneung.rice_wine_homepage.implement.cover.CoverAppender
import org.springframework.stereotype.Service

@Service
class CoverService(
    private val coverAppender: CoverAppender,
) {
    fun append(cover: Cover): Long {
        return coverAppender.append(cover)
    }
}