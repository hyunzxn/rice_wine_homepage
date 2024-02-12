package org.gangneung.ricewinehomepage.service.coverimage

import org.gangneung.ricewinehomepage.implement.coverimage.CoverImageUploader
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
