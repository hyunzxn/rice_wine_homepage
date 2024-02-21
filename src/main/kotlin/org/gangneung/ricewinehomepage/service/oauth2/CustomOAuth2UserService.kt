package org.gangneung.ricewinehomepage.service.oauth2

import org.gangneung.ricewinehomepage.domain.user.User
import org.gangneung.ricewinehomepage.implement.user.UserAppender
import org.gangneung.ricewinehomepage.implement.user.UserReader
import org.gangneung.ricewinehomepage.util.security.oauth2.CustomOAuth2User
import org.gangneung.ricewinehomepage.util.security.oauth2.UserDto
import org.gangneung.ricewinehomepage.util.security.oauth2.response.GoogleOAuth2Response
import org.gangneung.ricewinehomepage.util.security.oauth2.response.NaverOAuth2Response
import org.gangneung.ricewinehomepage.util.security.oauth2.response.OAuth2Response
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class CustomOAuth2UserService(
    private val userReader: UserReader,
    private val userAppender: UserAppender,
) : DefaultOAuth2UserService() {
    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val oauth2User = super.loadUser(userRequest)
        val oAuth2Response = getOAuth2Response(userRequest, oauth2User)
        val username = oAuth2Response.getProvider() + "-" + oAuth2Response.getProviderId()
        val existData = userReader.readByUsername(username)
        if (existData == null) {
            val newUser =
                User.createInstance(
                    username = username,
                    name = oAuth2Response.getName(),
                    email = oAuth2Response.getEmail(),
                    role = "ROLE_USER",
                )
            userAppender.append(newUser)
            val userDto =
                UserDto(
                    name = oAuth2Response.getName(),
                    username = username,
                    role = "ROLE_USER",
                )
            return CustomOAuth2User(userDto)
        } else {
            existData.update(oAuth2Response.getName(), oAuth2Response.getEmail()) // todo update 하는 부분 다시 별도로 객체 만들어서 해야할 수도? 트랜잭션 범위 때문에
            val userDto =
                UserDto(
                    name = oAuth2Response.getName(),
                    username = existData.name,
                    role = existData.role,
                )
            return CustomOAuth2User(userDto)
        }
    }

    private fun getOAuth2Response(
        userRequest: OAuth2UserRequest,
        oauth2User: OAuth2User,
    ): OAuth2Response {
        val registrationId = userRequest.clientRegistration.registrationId
        val oAuth2Response =
            if (registrationId.equals("naver")) {
                NaverOAuth2Response(oauth2User.attributes)
            } else if (registrationId.equals("google")) {
                GoogleOAuth2Response(oauth2User.attributes)
            } else {
                throw RuntimeException("OAuth2 소셜 로그인 에러")
            }
        return oAuth2Response
    }
}
