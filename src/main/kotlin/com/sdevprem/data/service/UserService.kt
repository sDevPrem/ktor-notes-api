package com.sdevprem.data.service

import com.sdevprem.data.model.User
import com.sdevprem.data.repository.UserRepository

class UserService(
    private val userRepository: UserRepository
) {
    fun isUserEmailExist(email: String) =
        userRepository.isUserEmailExist(email)

    fun getUserByEmail(email: String) =
        userRepository.getUserByEmail(email)

    fun insertUser(user: User) =
        userRepository.insertUser(user)
}