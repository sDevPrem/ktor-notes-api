package com.sdevprem.route

import com.sdevprem.data.auth.jwt.JwtConfig
import com.sdevprem.data.model.User
import com.sdevprem.data.service.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject


fun Route.authRoutes() {
    val secret = environment?.config?.propertyOrNull("jwt.secret")?.getString() ?: ""
    val userService by inject<UserService>()

    route("signup") {
        post {
            val user = call.receive<User>()
            try {
                val isUserExist = userService.isUserEmailExist(user.email)
                if (isUserExist) {
                    return@post call.respond(HttpStatusCode.BadRequest, "User email already exist")
                } else {
                    val id = userService.insertUser(user)
                    val token = JwtConfig.generateToken(user.also {
                        it["id"] = id
                    }, secret)
                    return@post call.respond(
                        HttpStatusCode.Created, mapOf(
                            "user" to user,
                            "token" to token
                        )
                    )
                }
            } catch (e: Exception) {
                println(e.message)
                call.respond(HttpStatusCode.InternalServerError, "Something went wrong")
            }
        }
    }

    route("login") {
        post {
            val user = call.receive<User>()
            try {
                val dbUser = userService.getUserByEmail(user.email)
                if (dbUser == null) {
                    return@post call.respond(HttpStatusCode.NotFound, "User not found")
                } else {
                    if (user.password != dbUser.password)
                        return@post call.respond(HttpStatusCode.BadRequest, "Invalid Credentials")
                    val token = JwtConfig.generateToken(dbUser, secret)
                    return@post call.respond(
                        HttpStatusCode.Accepted, mapOf(
                            "user" to dbUser,
                            "token" to token
                        )
                    )
                }
            } catch (e: Exception) {
                println(e.message)
                call.respond(HttpStatusCode.InternalServerError, "Something went wrong")
            }
        }
    }
}

fun Application.registerAuthRoute() {
    routing {
        authRoutes()
    }
}