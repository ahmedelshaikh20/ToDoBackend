package com.example.routes

import com.example.data.repository.UserRepository
import com.example.service.RegistrationParams
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.authRoutes(userRepository: UserRepository) {

  routing {
    route("/auth") {
      post("/register") {
        val body = call.receive<RegistrationParams>()
        val result = userRepository.registerUser(body)
        call.respond(result.statusCode, result)
      }
    }

  }


}
