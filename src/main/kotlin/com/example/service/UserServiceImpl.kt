package com.example.service

import com.example.authentication.hash
import com.example.data.model.User
import com.example.data.repository.toUser
import com.example.data.table.UserTable
import com.example.routes.dbQuery
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class UserServiceImpl : UserService {
  override suspend fun registerUser(registrationParams: RegistrationParams): User? {
    dbQuery {
      UserTable.insert { userTable ->
        userTable[email] = registrationParams.email
        userTable[name] = registrationParams.name
        userTable[hashPassword] = hash(registrationParams.password.toString())
      }
    }
    return findUserByEmail(registrationParams.email)

  }

  override suspend fun findUserByEmail(email: String): User? = dbQuery {
    val user = UserTable.selectAll().where { UserTable.email.eq(email) }
      .map {
        it.toUser()
      }
      .singleOrNull()
    return@dbQuery user

  }


}
