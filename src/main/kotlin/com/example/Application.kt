package com.example

import com.example.authentication.configureSecurity
import com.example.data.repository.UserRepositoryImpl
import com.example.routes.*
import com.example.service.UserServiceImpl
import io.ktor.server.application.*

fun main(args: Array<String>) {
  io.ktor.server.netty.EngineMain.main(args)

}

fun Application.module() {
  val userService = UserServiceImpl()
  val userRepository = UserRepositoryImpl(userService)
  configureDatabases()
  configureSerialization()
  configureSecurity()
  configureRouting(userRepository)
}
