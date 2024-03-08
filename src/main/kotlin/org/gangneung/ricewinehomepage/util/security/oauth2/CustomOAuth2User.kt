package org.gangneung.ricewinehomepage.util.security.oauth2

import org.gangneung.ricewinehomepage.util.security.CustomUser
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.OAuth2User

class CustomOAuth2User(
    private val userDto: UserDto,
) : OAuth2User, CustomUser() {
    override fun getName(): String {
        return userDto.name
    }

    override fun getAttributes(): MutableMap<String, Any>? {
        return null
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities = mutableListOf<GrantedAuthority>()
        authorities.add(GrantedAuthority { userDto.role })
        return authorities
    }

    fun getUsername(): String {
        return userDto.username
    }

    override fun commonGetUserName(): String {
        return userDto.username
    }
}
