package org.gangneung.rice_wine_homepage.presentation.coverimage

import org.gangneung.rice_wine_homepage.service.coverimage.CoverImageService
import org.gangneung.rice_wine_homepage.util.ApiResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/cover-images")
class CoverImageController(
    private val coverImageService: CoverImageService,
) {
    @PostMapping
    fun uploadCoverImage(@RequestParam("images") files: List<MultipartFile>): ApiResponse<List<String>> {
        return ApiResponse.ok("S3에 이미지를 정상적으로 업로드 하였습니다.", coverImageService.uploadCoverImage(files))
    }
}