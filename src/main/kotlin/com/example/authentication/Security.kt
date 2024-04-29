package com.example.authentication

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun Application.configureSecurity() {
  // Please read the jwt property from the config file if you are using EngineMain
  val jwtAudience = "jwt-audience"
  val jwtDomain = "todo-app"
  val jwtRealm = "ktor sample app"
  val jwtSecret = "secret"
  val jwtClaim = "email"
  JwtService.initialize()
  authentication {
    jwt {
      verifier(JwtService.instance.varifier)
      validate {
        val claim = it.payload.getClaim("email").asString()
        if (claim != null) {
          UserEmailPrincipal(claim)
        } else
          null
      }
    }
  }

  fun createAccessToken(email: String): String = JWT.create().withAudience(jwtAudience)
    .withClaim(jwtClaim, email).sign(Algorithm.HMAC256(jwtSecret))

  data class MySession(val count: Int = 0)
  install(Sessions) {
    cookie<MySession>("MY_SESSION") {
      cookie.extensions["SameSite"] = "lax"
    }
  }
  routing {
    get("/session/increment") {
      val session = call.sessions.get<MySession>() ?: MySession()
      call.sessions.set(session.copy(count = session.count + 1))
      call.respondText("Counter is ${session.count}. Refresh to increment.")
    }
  }
}
