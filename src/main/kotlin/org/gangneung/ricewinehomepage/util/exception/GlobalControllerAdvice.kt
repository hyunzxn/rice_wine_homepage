package org.gangneung.ricewinehomepage.util.exception

import org.gangneung.ricewinehomepage.util.logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalControllerAdvice {
    val log = logger()

    @ExceptionHandler
    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        log.error(e.message, e)
        return ResponseEntity
            .badRequest()
            .body(ErrorResponse.of(400, HttpStatus.BAD_REQUEST, e.message!!))
    }

    @ExceptionHandler
    fun handleRuntimeException(e: RuntimeException): ResponseEntity<ErrorResponse> {
        log.error(e.message, e)
        return ResponseEntity
            .internalServerError()
            .body(ErrorResponse.of(500, HttpStatus.INTERNAL_SERVER_ERROR, e.message!!))
    }
}
