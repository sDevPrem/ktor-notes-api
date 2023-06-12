package com.sdevprem.data.model

import org.ktorm.entity.Entity

interface User : Entity<User> {
    val id: Int
    val userName: String
    val password: String
    val email: String
}