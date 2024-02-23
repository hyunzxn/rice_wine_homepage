package org.gangneung.ricewinehomepage.util.security.oauth2

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames
import org.springframework.stereotype.Component

@Component
class SocialRegistration {
    @Value("\${oauth.naver.client-id}")
    private lateinit var naverClientId: String

    @Value("\${oauth.naver.client-secret}")
    private lateinit var naverClientSecret: String

    @Value("\${oauth.google.client-id}")
    private lateinit var googleClientId: String

    @Value("\${oauth.google.client-secret}")
    private lateinit var googleClientSecret: String

    fun naverClientRegistration(): ClientRegistration {
        return ClientRegistration.withRegistrationId("naver")
            .clientId(naverClientId)
            .clientSecret(naverClientSecret)
            .redirectUri("http://localhost:8080/login/oauth2/code/naver")
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .scope("name", "email")
            .authorizationUri("https://nid.naver.com/oauth2.0/authorize")
            .tokenUri("https://nid.naver.com/oauth2.0/token")
            .userInfoUri("https://openapi.naver.com/v1/nid/me")
            .userNameAttributeName("response")
            .build()
    }

    fun googleClientRegistration(): ClientRegistration {
        return ClientRegistration.withRegistrationId("google")
            .clientId(googleClientId)
            .clientSecret(googleClientSecret)
            .redirectUri("http://localhost:8080/login/oauth2/code/google")
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .scope("profile", "email")
            .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
            .tokenUri("https://www.googleapis.com/oauth2/v4/token")
            .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
            .issuerUri("https://accounts.google.com")
            .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
            .userNameAttributeName(IdTokenClaimNames.SUB)
            .build()
    }
}
