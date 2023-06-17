package com.sdevprem.data.service

import at.favre.lib.crypto.bcrypt.BCrypt
import com.sdevprem.data.model.User
import com.sdevprem.data.repository.UserRepository

class UserService(
    private val userRepository: UserRepository
) {
    fun isUserEmailExist(email: String) =
        userRepository.isUserEmailExist(email)

    fun getUserByEmail(email: String) =
        userRepository.getUserByEmail(email)

    fun insertUser(user: User): User {
        val hashedPassword = BCrypt.withDefaults().hashToString(12, user.password.toCharArray())
        val newId = userRepository.insertUser(user.copy(password = hashedPassword))
        return user.copy(id = newId)
    }

    fun isUserPasswordValid(userPassword: String, dbPassword: String) =
        BCrypt.verifyer().verify(userPassword.toCharArray(), dbPassword)
            .verified
}