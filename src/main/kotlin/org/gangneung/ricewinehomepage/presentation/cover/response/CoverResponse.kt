package org.gangneung.ricewinehomepage.presentation.cover.response

import org.gangneung.ricewinehomepage.domain.cover.Cover

data class CoverResponse(
    val content: String,
    val address: String,
    val callNumber: String,
    val snsName: String,
) {
    companion object {
        fun toResponse(cover: Cover): CoverResponse {
            return CoverResponse(
                content = cover.content,
                address = cover.address,
                callNumber = cover.callNumber,
                snsName = cover.snsName,
            )
        }
    }
}
