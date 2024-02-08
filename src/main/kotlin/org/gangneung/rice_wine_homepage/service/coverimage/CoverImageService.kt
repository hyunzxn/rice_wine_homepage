package org.gangneung.rice_wine_homepage.service.coverimage

import org.gangneung.rice_wine_homepage.implement.coverimage.CoverImageUploader
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class CoverImageService(
    private val coverImageUploader: CoverImageUploader,
) {
    fun uploadCoverImage(files: List<MultipartFile>): List<String> {
        return coverImageUploader.uploadCoverImage(files)
    }
}