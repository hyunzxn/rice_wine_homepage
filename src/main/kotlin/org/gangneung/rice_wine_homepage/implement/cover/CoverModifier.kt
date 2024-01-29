package org.gangneung.rice_wine_homepage.implement.cover

import org.gangneung.rice_wine_homepage.domain.cover.Cover
import org.gangneung.rice_wine_homepage.presentation.cover.request.CoverModify
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CoverModifier {
    @Transactional
    fun modify(cover: Cover, request: CoverModify) {
        if (request.isNotValidated()) {
            throw IllegalArgumentException("내용, 주소, 전화번호는 공백일 수 없습니다.")
        }
        cover.modify(
            request.content,
            request.address,
            request.callNumber,
            request.snsName
        )
    }
}