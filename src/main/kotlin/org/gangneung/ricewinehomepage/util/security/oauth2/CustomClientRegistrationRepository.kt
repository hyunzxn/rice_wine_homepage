package org.gangneung.ricewinehomepage.util.security.oauth2

import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository

@Configuration
class CustomClientRegistrationRepository(
    private val clientRegistration: SocialRegistration,
) {
    fun clientRegistrationRepository(): ClientRegistrationRepository {
        return InMemoryClientRegistrationRepository(
            clientRegistration.naverClientRegistration(),
            clientRegistration.googleClientRegistration(),
            clientRegistration.kakaoClientRegistration(),
        )
    }
}
