package com.packtpub.sunnat629.social_network.service

import com.packtpub.sunnat629.social_network.repository.UserByNameRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service


@Service
class CustomUserDetailsService: UserDetailsService {

    @Autowired
    private lateinit var userByNameRepository: UserByNameRepository

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails? {
        val user = userByNameRepository.getUserByName(username)


        println("************* ${user.password}")

        return org.springframework.security.core.userdetails.User(username, user.password,
                AuthorityUtils.createAuthorityList("USER"))
    }
}