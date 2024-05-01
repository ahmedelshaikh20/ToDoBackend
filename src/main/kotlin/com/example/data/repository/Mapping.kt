package com.example.data.repository

import com.example.data.model.note.Note
import com.example.data.model.user.User
import com.example.data.table.notetable.NoteTable
import com.example.data.table.usertable.UserTable.email
import com.example.data.table.usertable.UserTable.hashPassword
import com.example.data.table.usertable.UserTable.name
import org.jetbrains.exposed.sql.ResultRow


fun ResultRow.toUser(): User {
  return User(
    email = this[email],
    name = this[name],
    hashPassword = this[hashPassword]
  )
}

fun ResultRow.toNote(): Note {
  return Note(
    id = this[NoteTable.noteID],
    noteTitle = this[NoteTable.noteTitle],
    noteDescription = this[NoteTable.noteDescription],
    date = this[NoteTable.date],
    isChecked = this[NoteTable.isChecked]
  )
}
