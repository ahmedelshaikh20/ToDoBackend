package com.example.data

import com.example.data.table.notetable.NoteTable
import com.example.data.table.usertable.UserTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabases() {
  val driverClass = environment.config.property("storage.driverClassName").getString()
  val jdbcUrl = environment.config.property("storage.jdbcURL").getString()
  val db = Database.connect(provideDataSource(jdbcUrl, driverClass))
  transaction(db) {
    SchemaUtils.create(UserTable)
    SchemaUtils.create(NoteTable)

  }
}

private fun provideDataSource(url: String, driverClass: String): HikariDataSource {
  val hikariConfig = HikariConfig().apply {
    driverClassName = driverClass
    jdbcUrl = url
    maximumPoolSize = 3
    isAutoCommit = false
    transactionIsolation = "TRANSACTION_REPEATABLE_READ"
    validate()
  }
  return HikariDataSource(hikariConfig)
}

suspend fun <T> dbQuery(block: () -> T): T =
  withContext(Dispatchers.IO) {
    transaction { block() }
  }
