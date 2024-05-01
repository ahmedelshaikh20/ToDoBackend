package com.example

import com.example.authentication.configureSecurity
import com.example.data.configureDatabases
import com.example.data.noterepository.NoteRepositoryImpl
import com.example.data.repository.UserRepositoryImpl
import com.example.di.AppModule
import com.example.routes.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
  io.ktor.server.netty.EngineMain.main(args)

}

fun Application.module() {
  val userService = AppModule.provideUserService()
  val jwtService = AppModule.provideJwtService()
  val noteService = AppModule.provideNoteService()
  val noteRepository = NoteRepositoryImpl(noteService)
  val userRepository = UserRepositoryImpl(userService, jwtService)
  configureDatabases()
  configureSerialization()
  configureSecurity()
  configureRouting(userRepository ,noteRepository)
}
