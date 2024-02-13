package org.gangneung.ricewinehomepage.presentation.business

import org.gangneung.ricewinehomepage.presentation.business.request.BusinessAppend
import org.gangneung.ricewinehomepage.service.business.BusinessService
import org.gangneung.ricewinehomepage.util.ApiResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/business")
class BusinessController(
    private val businessService: BusinessService,
) {
    @PostMapping
    fun append(
        @RequestBody request: BusinessAppend,
    ): ApiResponse<String> {
        businessService.append(request.toBusiness())
        return ApiResponse.ok("비즈니스 문의 생성 완료", "비즈니스 문의 등록을 완료했습니다. 추후 연락 드리겠습니다.")
    }
}
