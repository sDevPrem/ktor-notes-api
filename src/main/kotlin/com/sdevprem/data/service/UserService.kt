package com.sdevprem.data.service

import com.sdevprem.data.model.User
import com.sdevprem.data.repository.UserRepository
import org.mindrot.jbcrypt.BCrypt

class UserService(
    private val userRepository: UserRepository
) {
    fun isUserEmailExist(email: String) =
        userRepository.isUserEmailExist(email)

    fun getUserByEmail(email: String) =
        userRepository.getUserByEmail(email)

    fun insertUser(user: User): User {
        val hashedPassword = BCrypt.hashpw(user.password, BCrypt.gensalt())
        val newId = userRepository.insertUser(user.copy(password = hashedPassword))
        return user.copy(id = newId)
    }

    fun isUserPasswordValid(userPassword: String, dbPassword: String) =
        BCrypt.checkpw(userPassword, dbPassword)
}