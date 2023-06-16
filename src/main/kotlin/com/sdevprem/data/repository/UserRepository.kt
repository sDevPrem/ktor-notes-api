package com.sdevprem.data.repository

import com.sdevprem.data.model.User
import com.sdevprem.data.schema.UserSchema
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.sequenceOf

class UserRepository(
    private val db: Database
) {

    private val Database.user
        get() = this.sequenceOf(UserSchema)

    fun isUserEmailExist(email: String): Boolean {
        val dbUser = db.from(UserSchema)
            .select(UserSchema.email)
            .where {
                UserSchema.email eq email
            }
        return dbUser.asIterable().firstOrNull() != null
    }

    fun getUserByEmail(email: String): User? {
        return db.from(UserSchema)
            .select()
            .where {
                UserSchema.email eq email
            }.map { UserSchema.createEntity(it) }
            .firstOrNull()
    }

    fun insertUser(user: User): Int {
        db.user
        return db.insertAndGenerateKey(UserSchema) {
            set(it.email, user.email)
            set(it.userName, user.userName)
            set(it.password, user.password)
        } as Int

    }
}