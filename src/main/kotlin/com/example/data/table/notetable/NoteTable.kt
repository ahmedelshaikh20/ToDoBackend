package com.example.data.table.notetable


import com.example.data.table.notetable.NoteTable.autoIncrement
import com.example.data.table.usertable.UserTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object NoteTable : Table() {

  val noteID  : Column<Int> = integer("noteID").autoIncrement()
  val email = varchar("email", 512).references(UserTable.email)
  val noteTitle = text("noteTitle")
  val noteDescription = text("noteDescription")
  val date = long("date")
  val isChecked = bool("isChecked")



  override val primaryKey: PrimaryKey?
    get() = PrimaryKey(noteID)
}
