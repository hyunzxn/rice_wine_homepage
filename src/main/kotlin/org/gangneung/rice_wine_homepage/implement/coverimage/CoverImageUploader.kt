package org.gangneung.rice_wine_homepage.implement.coverimage

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.ObjectMetadata
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.io.InputStream
import java.util.*

@Component
class CoverImageUploader(
    private val amazonS3Client: AmazonS3Client,
) {
    @Value("\${cloud.aws.s3.bucket}")
    private lateinit var bucket: String

    fun uploadCoverImage(files: List<MultipartFile>): List<String> {
        val imageUrls = mutableListOf<String>()
        for (file in files) {
            val fileName = UUID.randomUUID().toString() + "-" + file.originalFilename

            val objectMetaData = ObjectMetadata()
            objectMetaData.contentLength = file.size
            objectMetaData.contentType = file.contentType

            try {
                val inputStream: InputStream = file.inputStream
                amazonS3Client.putObject(bucket, fileName, inputStream, objectMetaData)
                val uploadImageUrl = amazonS3Client.getUrl(bucket, fileName).toString()
                imageUrls.add(uploadImageUrl)
            } catch (e: IOException) {
                throw RuntimeException("S3에 파일을 제대로 업로드 하지 못 했습니다.", e)
            }
        }
        return imageUrls
    }
}