package com.sdevprem.plugins

import com.sdevprem.data.auth.jwt.JwtConfig
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun Application.configureSecurity() {

    val secret = environment.config.propertyOrNull("jwt.secret")?.getString() ?: ""
    val myRealm = environment.config.propertyOrNull("jwt.realm")?.getString() ?: ""

    authentication {
        jwt("auth-jwt") {
            realm = myRealm
            verifier(
                JwtConfig.getVerifier(secret)
            )
            validate { credential ->
//                if (credential.payload.audience.contains(jwtAudience)) JWTPrincipal(credential.payload) else null
                //allow all
                JWTPrincipal(credential.payload)
            }
        }
    }
}
