package com.example.data.repository

import com.example.data.model.User
import com.example.service.RegistrationParams
import com.example.utils.BaseResponse

interface UserRepository {
  suspend fun registerUser(registrationParams: RegistrationParams):BaseResponse<Any>
  suspend fun loginUser(email:String,password:String):BaseResponse<Any>
}
