package org.gangneung.ricewinehomepage.util.security.oauth2.response

data class KakaoOAuth2Response(
    private val attribute: Map<String, Any>,
) : OAuth2Response {
    private val account = attribute["kakao_account"] as Map<*, *>
    private val profile = account["profile"] as Map<*, *>

    override fun getProvider(): String {
        return "kakao"
    }

    override fun getProviderId(): String {
        return attribute["id"].toString()
    }

    override fun getEmail(): String {
        return account["email"].toString()
    }

    override fun getName(): String {
        return profile["nickname"].toString()
    }
}
