package org.gangneung.ricewinehomepage.presentation.auth

import org.gangneung.ricewinehomepage.presentation.auth.request.Signup
import org.gangneung.ricewinehomepage.service.auth.AuthService
import org.gangneung.ricewinehomepage.util.ApiResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService,
) {
    @PostMapping("/signup")
    fun signup(
        @RequestBody request: Signup,
    ): ApiResponse<String> {
        request.validate()
        authService.signup(request)
        return ApiResponse.ok("회원가입 성공", "회원가입을 환영합니다.")
    }
}
