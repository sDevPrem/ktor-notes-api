package com.sdevprem.route

import com.sdevprem.data.DBHelper
import com.sdevprem.data.auth.jwt.JwtConfig
import com.sdevprem.data.model.Note
import com.sdevprem.data.repository.NotesRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureNotesRoute() {
    routing {
        authenticate("auth-jwt") {
            route("/notes") {
                notesRoutes()
            }
        }
    }
}

private fun Route.notesRoutes() {
    val notesRepository = NotesRepository(DBHelper.database)

    get {
        return@get call.respond(HttpStatusCode.OK, notesRepository.getNotes(call.getUid()))
    }
    get("/{id}") {
        val id = call.parameters["id"]?.toInt()
            ?: return@get call.respond(HttpStatusCode.BadRequest, "Provide note id")

        return@get notesRepository.getNotesById(call.getUid(), id)?.let {
            call.respond(HttpStatusCode.OK, it)
        } ?: call.respond(HttpStatusCode.NotFound)
    }
    post {
        val note = call.receive<Note>()
        val newId = notesRepository.insertNote(call.getUid(), note)
        call.respond(HttpStatusCode.Created, note.apply {
            this["id"] = newId
        })
    }
    delete("/{id}") {
        val id = call.parameters["id"]?.toInt()
            ?: return@delete call.respond(HttpStatusCode.BadRequest, "Provide note id")
        val rowsAffected = notesRepository.deleteNote(call.getUid(), id)
        if (rowsAffected < 1)
            call.respond(HttpStatusCode.NotFound, "Cannot find notes for the given ID")
        else
            call.respond(HttpStatusCode.OK, "Note is deleted")
    }
    put("/{id}") {
        val id = call.parameters["id"]?.toInt()
            ?: return@put call.respond(HttpStatusCode.BadRequest, "Provide note id")

        val note = call.receive<Note>()
        val rowsAffected = notesRepository.updateNote(call.getUid(), id, note)
        if (rowsAffected < 1)
            call.respond(HttpStatusCode.NotFound, "Cannot find notes for the given ID")
        else
            call.respond(HttpStatusCode.OK, note.also {
                it["id"] = id
            })

    }
}

private fun ApplicationCall.getUid(): Int {
    val principal = principal<JWTPrincipal>()
    return principal!!.payload.getClaim(JwtConfig.USER_ID_CLAIM_NAME).asInt()
}