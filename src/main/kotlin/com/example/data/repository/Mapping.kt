package com.example.data.repository

import com.example.data.model.User
import com.example.data.table.UserTable.email
import com.example.data.table.UserTable.hashPassword
import com.example.data.table.UserTable.name
import org.jetbrains.exposed.sql.ResultRow


fun ResultRow.toUser(): User {
  return User(
    email = email.name,
    name = name.name,
    hashPassword = hashPassword.name
  )
}
