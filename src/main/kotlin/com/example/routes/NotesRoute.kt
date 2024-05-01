package com.example.routes

import com.example.authentication.UserEmailPrincipal
import com.example.data.model.note.Note
import com.example.data.noterepository.NoteRepository
import com.example.utils.BaseResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureNotesRoutes(noteRepository: NoteRepository) {
  routing {
    route("/notes") {

      authenticate() {
        post("/create") {

          val note = try {
            call.receive<Note>()
          } catch (e: Exception) {
            println()
            call.respond(HttpStatusCode.BadRequest, BaseResponse.ErrorResponse<Any>(message = "Missing Fields"))
            return@post
          }
          try {
            val email = call.principal<UserEmailPrincipal>()?.email
            if (email != null) {
              val result = noteRepository.addNote(note, email)
              call.respond(HttpStatusCode.OK, result)
            }

          } catch (e: Exception) {
            call.respond(
              HttpStatusCode.Conflict,
              BaseResponse.ErrorResponse<Any>(message = "Some Problem Occurred", error = e.message)
            )
          }
        }
        //Get All Notes
        get {
          try {
            val email = call.principal<UserEmailPrincipal>()?.email
            if (email != null) {
              val notesResult = noteRepository.getAllNotes(email)
              call.respond(HttpStatusCode.OK, notesResult)
            }

          } catch (e: Exception) {
            call.respond(
              HttpStatusCode.Conflict,
              BaseResponse.ErrorResponse<Any>(message = "Some Problem Occurred", error = e.message)
            )

          }


        }






      }

    }

  }
}
