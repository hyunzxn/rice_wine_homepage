package org.gangneung.rice_wine_homepage.implement.coverimage

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.ObjectMetadata
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.io.InputStream
import java.util.*
import java.util.concurrent.TimeUnit

@Component
class CoverImageUploader(
    private val amazonS3Client: AmazonS3Client,
    private val redisTemplate: RedisTemplate<String, String>,
) {
    @Value("\${cloud.aws.s3.bucket}")
    private lateinit var bucket: String

    fun uploadCoverImage(files: List<MultipartFile>): List<String> {
        val imageUrls = mutableListOf<String>()
        val operation = redisTemplate.opsForValue()
        for (file in files) {
            val fileName = UUID.randomUUID().toString() + "-" + file.originalFilename

            val objectMetaData = ObjectMetadata()
            objectMetaData.contentLength = file.size
            objectMetaData.contentType = file.contentType

            try {
                val inputStream: InputStream = file.inputStream
                amazonS3Client.putObject(bucket, fileName, inputStream, objectMetaData)
                val uploadImageUrl = amazonS3Client.getUrl(bucket, fileName).toString()
                operation.set(fileName, uploadImageUrl, 61, TimeUnit.MINUTES) // 삭제 스케줄이 1시간마다 돌기 때문에 TTL을 그것보다 길게 설정
                imageUrls.add(uploadImageUrl)
            } catch (e: IOException) {
                throw RuntimeException("S3에 파일을 제대로 업로드 하지 못 했습니다.", e)
            }
        }
        return imageUrls
    }
}