package com.example.di

import com.example.authentication.JwtService
import com.example.service.userservice.UserService
import com.example.service.userservice.UserServiceImpl
import com.example.service.noteservice.NoteService
import com.example.service.noteservice.NoteServiceImpl

object AppModule {
  fun provideJwtService(): JwtService {
    JwtService.initialize()
    val jwtServiceInstance = JwtService.instance
    return jwtServiceInstance
  }

  fun provideUserService(): UserService {
    return UserServiceImpl()
  }


  fun provideNoteService(): NoteService {
    return NoteServiceImpl()
  }


}
