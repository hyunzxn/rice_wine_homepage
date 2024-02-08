package org.gangneung.rice_wine_homepage.implement.coverimage

import org.gangneung.rice_wine_homepage.domain.cover.Cover
import org.gangneung.rice_wine_homepage.domain.cover.CoverImage
import org.gangneung.rice_wine_homepage.repository.coverimage.CoverImageRepository
import org.gangneung.rice_wine_homepage.util.aws.S3Utils
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class CoverImageAppender(
    private val coverImageRepository: CoverImageRepository,
    private val s3Utils: S3Utils,
) {
    @Value("\${cloud.aws.s3.bucket}")
    private lateinit var bucket: String

    fun append(cover: Cover) {
        val coverImages = mutableListOf<CoverImage>()
        val objectKeys = s3Utils.getObjectKeys(bucket)
        for (key in objectKeys) {
            val url = s3Utils.getUrl(bucket, key)
            val newCoverImage = CoverImage.createInstance(url, key, cover)
            coverImages.add(newCoverImage)
        }
        coverImageRepository.saveAll(coverImages)
    }
}