package com.sdevprem.data.auth.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.sdevprem.data.model.User

object JwtConfig {
    const val USER_EMAIL_CLAIM_NAME = "email"
    const val USER_ID_CLAIM_NAME = "id"

    fun getVerifier(secret: String): JWTVerifier {
        return JWT
            .require(Algorithm.HMAC256(secret))
            .build()
    }

    fun generateToken(user: User, secret: String): String {
        return JWT.create()
            .withClaim(USER_EMAIL_CLAIM_NAME, user.email)
            .withClaim(USER_ID_CLAIM_NAME, user.id)
            .sign(Algorithm.HMAC256(secret))
    }
}