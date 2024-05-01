package com.example.service.noteservice

import com.example.data.model.note.Note

interface NoteService {
  suspend fun getAllNotes(email: String?):List<Note>
  suspend fun addNote(note: Note , email: String):Int
  suspend fun updateNote(note: Note,email: String)
}
