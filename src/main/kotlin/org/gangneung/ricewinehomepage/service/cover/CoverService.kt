package org.gangneung.ricewinehomepage.service.cover

import org.gangneung.ricewinehomepage.domain.cover.Cover
import org.gangneung.ricewinehomepage.implement.cover.CoverAppender
import org.gangneung.ricewinehomepage.implement.cover.CoverModifier
import org.gangneung.ricewinehomepage.implement.coverimage.CoverImageAppender
import org.gangneung.ricewinehomepage.presentation.cover.request.CoverModify
import org.gangneung.ricewinehomepage.presentation.cover.request.CoverReader
import org.gangneung.ricewinehomepage.presentation.cover.response.CoverResponse
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

    fun modify(
        id: Long,
        request: CoverModify,
    ): Long {
        val cover = coverReader.readBy(id)
        coverModifier.modify(cover, request)
        return cover.id!!
    }
}
