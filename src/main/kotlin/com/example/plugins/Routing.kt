package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
  routing {
    get("/") {
      call.respondText("Hello World!")
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
