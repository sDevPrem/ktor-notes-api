package com.sdevprem.data.model

import org.ktorm.entity.Entity

interface User : Entity<User> {
    val id: Int
    val userName: String
    val email: String
    val password: String

    fun copy(
        id: Int = this.id,
        userName: String = this.userName,
        email: String = this.email,
        password: String = this.password
    ) = copy().apply {
        if (this.id < 1) this["id"] = id //because primary key start from 1 and can't be changed if set
        this["userName"] = userName
        this["email"] = email
        this["password"] = password
    }
}