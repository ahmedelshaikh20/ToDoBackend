package com.example.data.repository

import com.example.authentication.JwtService
import com.example.service.RegistrationParams
import com.example.service.UserService
import com.example.utils.BaseResponse

class UserRepositoryImpl(private val userService: UserService) : UserRepository {
  override suspend fun registerUser(registrationParams: RegistrationParams): BaseResponse<Any> {
    if (isEmailExist(registrationParams.email)) {
      return BaseResponse.ErrorResponse(message = "Email already registered")
    } else {
      val user = userService.registerUser(registrationParams)
      return if (user != null) {
        val token = JwtService.instance.generateToken(user)
        user.authToken=token
        BaseResponse.SuccessResponse(data = user, message = "Success")
      } else {
        BaseResponse.ErrorResponse(message = "Error in Registering User")
      }
    }
  }

  override suspend fun loginUser(email: String, password: String): BaseResponse<Any> {
    TODO("Not yet implemented")
  }

  private suspend fun isEmailExist(email: String): Boolean {
    return userService.findUserByEmail(email) != null
  }
}
