package org.gangneung.ricewinehomepage.util.security.oauth2.response

data class NaverOAuth2Response(
    private val attribute: Map<String, Any>,
) : OAuth2Response {
    private val responseAttribute = attribute["response"] as Map<*, *>

    override fun getProvider(): String {
        return "naver"
    }

    override fun getProviderId(): String {
        return responseAttribute["id"].toString()
    }

    override fun getEmail(): String {
        return responseAttribute["email"].toString()
    }

    override fun getName(): String {
        return responseAttribute["name"].toString()
    }
}
