package org.gangneung.rice_wine_homepage.implement.coverimage

import org.gangneung.rice_wine_homepage.util.aws.S3Utils
import org.gangneung.rice_wine_homepage.util.logger
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class CoverImageRemover(
    private val s3Utils: S3Utils,
    private val coverImageReader: CoverImageReader,
    private val redisTemplate: RedisTemplate<String, Any>,
) {
    private val log = logger()

    @Value("\${cloud.aws.s3.bucket}")
    private lateinit var bucket: String

    @Scheduled(fixedRate = 3600000) // 1시간마다 호출
    fun delete() {
        val coverImages = coverImageReader.readAll()
        if (coverImages.isEmpty()) {
            return
        }

        val imageUrlsInDB = coverImages.map { it.imageUrl }
        val redisKeys = redisTemplate.keys("*")
        for (key in redisKeys) {
            val imageUrlInRedis = redisTemplate.opsForValue().get(key)
            if (!imageUrlsInDB.contains(imageUrlInRedis)) {
                s3Utils.deleteObject(bucket, key)
                log.info("소개글 작성을 완료하지 않아 S3에 있는 객체를 삭제하였습니다.")
            }
        }
    }
}