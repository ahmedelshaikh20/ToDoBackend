package com.example.routes

import com.example.data.noterepository.NoteRepository
import com.example.data.repository.UserRepository
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(userRepository: UserRepository, noteRepository: NoteRepository) {

  authRoutes(userRepository)
  configureNotesRoutes(noteRepository)
  routing {
    get("/") {
      call.respondText("Hello World!")
    }
    get("/note/{id}") {
      val id = call.parameters["id"]
      call.respond("${id}")

    }
    get("/note") {
      val id = call.request.queryParameters["id"]
      call.respond("${id}")

    }


    //
    route("/notes") {
      route("/create") {
        //localhost:8080/notes/create
        post {
          val body = call.receive<String>()
          call.respond(body)
        }
      }
      delete {
        // Delete Request
        //localhost:8080/notes/
        val body = call.receive<String>()
        call.respond(body)
      }
    }
  }
}
