package com.example.authentication

import io.ktor.server.auth.*

data class UserEmailPrincipal(val email:String) : Principal
