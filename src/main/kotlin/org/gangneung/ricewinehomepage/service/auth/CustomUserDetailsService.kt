package org.gangneung.ricewinehomepage.service.auth

import org.gangneung.ricewinehomepage.domain.user.User
import org.gangneung.ricewinehomepage.implement.user.UserReader
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userReader: UserReader,
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        userReader.readByUsername(username)?.let {
            return CustomUserDetails(it)
        } ?: throw UsernameNotFoundException("$username 에 해당하는 유저를 조회할 수 없습니다.")
    }
}

class CustomUserDetails(
    private val user: User,
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val collection = ArrayList<GrantedAuthority>()
        collection.add(GrantedAuthority { user.role })
        return collection
    }

    override fun getPassword(): String {
        return user.password!!
    }

    override fun getUsername(): String {
        return user.username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}
