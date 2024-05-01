package com.example.service.userservice

import com.example.authentication.hash
import com.example.data.model.user.User
import com.example.data.repository.toUser
import com.example.data.table.usertable.UserTable
import com.example.data.dbQuery
import com.example.service.RegistrationParams
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class UserServiceImpl : UserService {
  override suspend fun registerUser(registrationParams: RegistrationParams): User? {
    dbQuery {
      UserTable.insert { userTable ->
        userTable[email] = registrationParams.email
        userTable[name] = registrationParams.name
        userTable[hashPassword] = hash(registrationParams.password)
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

  override suspend fun loginUser(email: String, password: String): User? {
    val user = findUserByEmail(email) ?: return null
    return if (user.hashPassword == hash(password)) {
      user
    } else {
      null
    }
  }


}
