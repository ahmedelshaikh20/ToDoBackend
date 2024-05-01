package com.example.service.userservice

import com.example.data.model.user.User
import com.example.service.RegistrationParams

interface UserService {
  suspend fun registerUser(registrationParams: RegistrationParams): User?
  suspend fun findUserByEmail(email: String): User?
  suspend fun loginUser(email: String ,password:String): User?

}

