package org.gangneung.ricewinehomepage.util.security.oauth2.response

data class GoogleOAuth2Response(
    private val attribute: Map<String, Any>,
) : OAuth2Response {
    override fun getProvider(): String {
        return "google"
    }

    override fun getProviderId(): String {
        return attribute["sub"].toString()
    }

    override fun getEmail(): String {
        return attribute["email"].toString()
    }

    override fun getName(): String {
        return attribute["name"].toString()
    }
}
