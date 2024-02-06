package org.gangneung.rice_wine_homepage.presentation.coverimage.request

import org.springframework.web.multipart.MultipartFile

data class CoverImageUpload(
    val title: String,
    val imageFile: MultipartFile,
)
