package org.gangneung.ricewinehomepage.util.security.jwt

import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.Date
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

@Component
class JwtUtil(
    @Value("\${spring.jwt.secret}") secret: String,
) {
    private val secretKey: SecretKey =
        SecretKeySpec(secret.toByteArray(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().algorithm)

    fun getUsername(token: String): String {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).payload["username"] as String
    }

    fun getRole(token: String): String {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).payload["role"] as String
    }

    fun getType(token: String): String {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).payload["type"] as String
    }

    fun isExpired(token: String): Boolean {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).payload.expiration.before(Date())
    }

    fun createJwt(
        username: String,
        role: String,
        type: String,
        expiredMs: Long,
    ): String {
        return Jwts.builder()
            .claim("username", username)
            .claim("role", role)
            .claim("type", type)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + expiredMs))
            .signWith(secretKey)
            .compact()
    }
}
