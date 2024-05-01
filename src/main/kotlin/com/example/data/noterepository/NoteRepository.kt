package com.example.data.noterepository

import com.example.data.model.note.Note
import com.example.utils.BaseResponse

interface NoteRepository {
  suspend fun getAllNotes (email:String):BaseResponse<Any>
  suspend fun addNote (note: Note, email: String): BaseResponse<Any>
  suspend fun updateNote(note: Note, id: String): BaseResponse<Any>

}
