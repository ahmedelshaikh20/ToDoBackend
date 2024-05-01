package com.example.service.noteservice

import com.example.data.dbQuery
import com.example.data.model.note.Note
import com.example.data.repository.toNote
import com.example.data.table.notetable.NoteTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update

class NoteServiceImpl : NoteService {
  override suspend fun getAllNotes(email: String?): List<Note> = dbQuery {
    if (email != null) {
      NoteTable.selectAll().where(NoteTable.email.eq(email))
        .map {
          it.toNote()
        }
    } else {
      NoteTable.selectAll()
        .map {
          it.toNote()
        }
    }

  }


  override suspend fun addNote(note: Note, email: String): Int {
    val res = getAllNotes(null)
    var nextID = 1;
    if (res.isNotEmpty()) {
      nextID = res[res.lastIndex].id + 1
    }
    dbQuery {
      NoteTable.insert { noteTable ->

        noteTable[noteTitle] = note.noteTitle
        noteTable[noteDescription] = note.noteDescription
        noteTable[date] = note.date
        noteTable[NoteTable.email] = email
        noteTable[isChecked] = note.isChecked

      }
    }
    return nextID;
  }

  override suspend fun updateNote(note: Note, email: String) {
    dbQuery {
      NoteTable.update(where = { NoteTable.email.eq(email) and NoteTable.noteID.eq(note.id) }) { noteTable ->
        noteTable[noteTitle] = note.noteTitle
        noteTable[noteDescription] = noteDescription
        noteTable[date] = note.date
        noteTable[NoteTable.email] = email
        noteTable[isChecked] = note.isChecked

      }
    }
  }
}
