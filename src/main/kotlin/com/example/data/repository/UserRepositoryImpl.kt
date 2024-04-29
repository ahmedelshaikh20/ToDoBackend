package com.example.data.repository

import com.beust.klaxon.Parser
import com.example.authentication.JwtService
import com.example.authentication.hash
import com.example.service.LoginParams
import com.example.service.RegistrationParams
import com.example.service.UserService
import com.example.utils.BaseResponse
import kotlinx.serialization.json.*
import java.lang.StringBuilder

class UserRepositoryImpl(private val userService: UserService, private val jwtService: JwtService) : UserRepository {
  override suspend fun registerUser(registrationParams: RegistrationParams): BaseResponse<Any> {
    if (isMissingParams(registrationParams)) {
      return BaseResponse.ErrorResponse(message = "Missing Fields")
    }
    if (isEmailExist(registrationParams.email)) {
      return BaseResponse.ErrorResponse(message = "Email already registered")
    } else {
      val user = userService.registerUser(registrationParams)
      return if (user != null) {
        val token = JwtService.instance.generateToken(user)
        user.authToken = token
        BaseResponse.SuccessResponse(data = user, message = "Success")
      } else {
        BaseResponse.ErrorResponse(message = "Error in Registering User")
      }
    }
  }

  private fun isMissingLoginParams(loginParams: LoginParams): Boolean {
    return loginParams.email == "" || loginParams.password == ""

  }

  override suspend fun loginUser(loginParams: LoginParams): BaseResponse<Any> {
    if (isMissingLoginParams(loginParams))
      return BaseResponse.ErrorResponse(message = "Missing Field")
    else {
      val user = userService.findUserByEmail(loginParams.email)
      if (user == null) {
        return BaseResponse.ErrorResponse(message = "No Such Email Registered")
      } else if (user.hashPassword == hash(loginParams.password)) {
        val parser: Parser = Parser()
        val stringBuilder: StringBuilder = StringBuilder("{\"token\":\"${jwtService.generateToken(user)}\"}")
        val json = parser.parse(stringBuilder)

        return BaseResponse.SuccessResponse(

          data = json,
          message = "Success"
        )
      } else
        return BaseResponse.ErrorResponse(message = "Wrong Password")


    }

  }

  private suspend fun isEmailExist(email: String): Boolean {
    return userService.findUserByEmail(email) != null
  }

  private fun isMissingParams(registrationParams: RegistrationParams): Boolean {
    return registrationParams.email == "" || registrationParams.name == "" || registrationParams.password == ""
  }
}
