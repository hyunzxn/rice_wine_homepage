package org.gangneung.ricewinehomepage.presentation.review

import org.gangneung.ricewinehomepage.presentation.review.request.ReviewAppend
import org.gangneung.ricewinehomepage.service.review.ReviewService
import org.gangneung.ricewinehomepage.util.ApiResponse
import org.gangneung.ricewinehomepage.util.logger
import org.gangneung.ricewinehomepage.util.security.oauth2.CustomOAuth2User
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/reviews")
class ReviewController(
    private val reviewService: ReviewService,
) {
    private val log = logger()

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    fun append(
        @RequestBody request: ReviewAppend,
        @AuthenticationPrincipal user: CustomOAuth2User,
    ): ApiResponse<String> {
        val username = user.getUsername()
        log.info("username={}", username)
        reviewService.append(username, request)
        return ApiResponse.ok("고객 리뷰 등록 성공", "리뷰를 등록했습니다. 소중한 의견 감사드립니다.")
    }
}
