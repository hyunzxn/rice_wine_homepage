package org.gangneung.ricewinehomepage.util.security.oauth2.response

interface OAuth2Response {
    fun getProvider(): String

    fun getProviderId(): String

    fun getEmail(): String

    fun getName(): String
}
