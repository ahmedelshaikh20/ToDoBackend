package com.example.service

import com.example.data.model.User

interface UserService {
  suspend fun registerUser(registrationParams: RegistrationParams): User?
  suspend fun findUserByEmail(email: String): User?

}

