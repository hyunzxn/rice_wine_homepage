package org.gangneung.rice_wine_homepage.util.exception

import org.springframework.http.HttpStatus

data class ErrorResponse(
    val statusCode: Int,
    val httpStatus: HttpStatus,
    val message: String,
) {
    companion object {
        fun of(statusCode: Int, httpStatus: HttpStatus, message: String): ErrorResponse {
            return ErrorResponse(
                statusCode = statusCode,
                httpStatus = httpStatus,
                message = message
            )
        }
    }
}