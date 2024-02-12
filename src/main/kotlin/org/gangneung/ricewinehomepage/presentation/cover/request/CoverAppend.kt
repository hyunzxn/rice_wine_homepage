package org.gangneung.ricewinehomepage.presentation.cover.request

import org.gangneung.ricewinehomepage.domain.cover.Cover

data class CoverAppend(
    val content: String,
    val address: String,
    val callNumber: String,
    val snsName: String,
) {
    fun toCover(): Cover {
        if (content.isBlank() || address.isBlank() || callNumber.isBlank()) {
            throw IllegalArgumentException("내용, 주소, 전화번호는 공백일 수 없습니다.")
        }
        return Cover.createInstance(
            content = content,
            address = address,
            callNumber = callNumber,
            snsName = snsName,
        )
    }
}
