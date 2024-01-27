package org.gangneung.rice_wine_homepage.presentation.cover

import org.gangneung.rice_wine_homepage.presentation.cover.request.CoverAppend
import org.gangneung.rice_wine_homepage.service.cover.CoverService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/covers")
class CoverController(
    private val coverService: CoverService,
) {
    @PostMapping()
    fun append(@RequestBody request: CoverAppend): ResponseEntity<Long> {
        return ResponseEntity.ok().body(coverService.append(request.toCover()))
    }
}