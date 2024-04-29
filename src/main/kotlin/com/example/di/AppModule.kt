package com.example.di

import com.example.authentication.JwtService
import com.example.service.UserService
import com.example.service.UserServiceImpl

object AppModule {
  fun provideJwtService(): JwtService {
    JwtService.initialize()
    val jwtServiceInstance = JwtService.instance
    return jwtServiceInstance
  }

  fun provideUserService(): UserService {
    return UserServiceImpl()
  }


}
