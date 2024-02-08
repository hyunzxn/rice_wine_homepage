package org.gangneung.rice_wine_homepage.util.aws

import com.amazonaws.services.s3.AmazonS3Client
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class S3Utils(
    private val amazonS3Client: AmazonS3Client,
) {
    fun getUrl(bucket: String, key: String): String {
        return amazonS3Client.getUrl(bucket, key).toString()
    }

    fun getObjectKeys(bucket: String): List<String> {
        val keys = mutableListOf<String>()
        val objects = amazonS3Client.listObjects(bucket)
        for (summary in objects.objectSummaries) {
            keys.add(summary.key)
        }
        return keys
    }
}