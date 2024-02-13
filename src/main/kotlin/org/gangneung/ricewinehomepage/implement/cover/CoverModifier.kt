package org.gangneung.ricewinehomepage.implement.cover

import org.gangneung.ricewinehomepage.domain.cover.Cover
import org.gangneung.ricewinehomepage.presentation.cover.request.CoverModify
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CoverModifier {
    @Transactional
    fun modify(
        cover: Cover,
        request: CoverModify,
    ) {
        if (request.isNotValidated()) {
            throw IllegalArgumentException("내용, 주소, 전화번호는 공백일 수 없습니다.")
        }
        cover.modify(
            request.content,
            request.address,
            request.callNumber,
            request.snsName,
        )
    }
}
