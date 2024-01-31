package org.gangneung.rice_wine_homepage.presentation.coverimage

import org.gangneung.rice_wine_homepage.service.coverimage.CoverImageService
import org.springframework.web.bind.annotation.RestController

@RestController
class CoverImageController(
    private val coverImageService: CoverImageService,
) {
}