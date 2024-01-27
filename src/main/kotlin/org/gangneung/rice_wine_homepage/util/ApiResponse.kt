package org.gangneung.rice_wine_homepage.util

import org.springframework.http.HttpStatus

data class ApiResponse<T>(
    val httpStatus: HttpStatus,
    val message: String,
    val body: T,
) {
    companion object {
        fun <T> ok(message: String, body: T): ApiResponse<T> {
            return ApiResponse(
                httpStatus = HttpStatus.OK,
                message = message,
                body = body
            )
        }

        fun <T> of(httpStatus: HttpStatus, message: String, body: T): ApiResponse<T> {
            return ApiResponse(
                httpStatus = httpStatus,
                message = message,
                body = body
            )
        }
    }
}