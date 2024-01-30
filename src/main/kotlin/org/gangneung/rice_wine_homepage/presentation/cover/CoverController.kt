package org.gangneung.rice_wine_homepage.presentation.cover

import org.gangneung.rice_wine_homepage.presentation.cover.request.CoverAppend
import org.gangneung.rice_wine_homepage.presentation.cover.request.CoverModify
import org.gangneung.rice_wine_homepage.presentation.cover.response.CoverResponse
import org.gangneung.rice_wine_homepage.service.cover.CoverService
import org.gangneung.rice_wine_homepage.util.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/covers")
class CoverController(
    private val coverService: CoverService,
) {
    @GetMapping
    fun get(): ApiResponse<CoverResponse> {
        return ApiResponse.ok("소개글 조회가 완료됐습니다", coverService.get())
    }

    @PostMapping
    fun append(@RequestBody request: CoverAppend): ApiResponse<Long> {
        return ApiResponse.ok("소개글 작성이 완료됐습니다", coverService.append(request.toCover()))
    }

    @PutMapping("/{id}")
    fun modify(@RequestBody request: CoverModify, @PathVariable id: Long): ApiResponse<Long> {
        return ApiResponse.ok("소개글 수정이 완료됐습니다", coverService.modify(id, request))
    }
}