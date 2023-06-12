package com.sdevprem.data.schema

import com.sdevprem.data.model.User
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object UserSchema : Table<User>("t_user") {
    val id = int("id").primaryKey().bindTo { it.id }
    val userName = varchar("userName").bindTo { it.userName }
    val email = varchar("email").bindTo { it.email }
    val password = varchar("password").bindTo { it.password }
}