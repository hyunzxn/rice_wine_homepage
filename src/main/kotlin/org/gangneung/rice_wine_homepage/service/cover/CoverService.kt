package org.gangneung.rice_wine_homepage.service.cover

import org.gangneung.rice_wine_homepage.domain.cover.Cover
import org.gangneung.rice_wine_homepage.implement.cover.CoverAppender
import org.gangneung.rice_wine_homepage.implement.cover.CoverModifier
import org.gangneung.rice_wine_homepage.presentation.cover.request.CoverModify
import org.gangneung.rice_wine_homepage.presentation.cover.request.CoverReader
import org.springframework.stereotype.Service

@Service
class CoverService(
    private val coverReader: CoverReader,
    private val coverAppender: CoverAppender,
    private val coverModifier: CoverModifier,
) {
    fun append(cover: Cover): Long {
        return coverAppender.append(cover)
    }

    fun modify(id: Long, request: CoverModify): Long {
        val cover = coverReader.readBy(id)
        coverModifier.modify(cover, request)
        return cover.id!!
    }
}