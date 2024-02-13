package org.gangneung.ricewinehomepage.implement.coverimage

import org.gangneung.ricewinehomepage.domain.cover.Cover
import org.gangneung.ricewinehomepage.domain.cover.CoverImage
import org.gangneung.ricewinehomepage.repository.coverimage.CoverImageRepository
import org.gangneung.ricewinehomepage.util.aws.S3Utils
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
