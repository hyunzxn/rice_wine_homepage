package org.gangneung.rice_wine_homepage.service.cover

import org.gangneung.rice_wine_homepage.domain.cover.Cover
import org.gangneung.rice_wine_homepage.implement.cover.CoverAppender
import org.gangneung.rice_wine_homepage.implement.cover.CoverModifier
import org.gangneung.rice_wine_homepage.implement.coverimage.CoverImageAppender
import org.gangneung.rice_wine_homepage.presentation.cover.request.CoverModify
import org.gangneung.rice_wine_homepage.presentation.cover.request.CoverReader
import org.gangneung.rice_wine_homepage.presentation.cover.response.CoverResponse
import org.springframework.stereotype.Service

@Service
class CoverService(
    private val coverReader: CoverReader,
    private val coverAppender: CoverAppender,
    private val coverModifier: CoverModifier,
    private val coverImageAppender: CoverImageAppender,
) {
    fun get(): CoverResponse {
        return CoverResponse.toResponse(coverReader.readBy(1L))
    }

    fun append(cover: Cover): Long {
        val newCover = coverAppender.append(cover)
        coverImageAppender.append(newCover)
        return newCover.id!!
    }

    fun modify(id: Long, request: CoverModify): Long {
        val cover = coverReader.readBy(id)
        coverModifier.modify(cover, request)
        return cover.id!!
    }
}