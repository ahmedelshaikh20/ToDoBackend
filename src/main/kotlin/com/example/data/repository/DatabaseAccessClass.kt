package com.example.data.repository

import com.example.data.model.User
import com.example.data.table.UserTable
import com.example.routes.dbQuery
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class DatabaseAccessClass {

  suspend fun addUser(user: User) =
    dbQuery {
      UserTable.insert { userTable ->
        userTable[email] = user.email
        userTable[name] = user.name
        userTable[hashPassword] = user.hashPassword
      }
    }


  suspend fun findUser(email: String) = dbQuery {
    UserTable.selectAll().where { UserTable.email.eq(email) }
      .map { it.toUser() }
      .singleOrNull()
  }
}
