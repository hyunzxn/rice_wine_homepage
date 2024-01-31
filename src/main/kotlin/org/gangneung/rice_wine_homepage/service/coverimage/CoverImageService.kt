package org.gangneung.rice_wine_homepage.service.coverimage

import org.gangneung.rice_wine_homepage.implement.coverimage.CoverImageUploader
import org.springframework.stereotype.Service

@Service
class CoverImageService(
    private val coverImageUploader: CoverImageUploader,
) {
}