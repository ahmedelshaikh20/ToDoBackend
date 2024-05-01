package com.example.data.noterepository

import com.example.data.model.note.Note
import com.example.service.noteservice.NoteService
import com.example.utils.BaseResponse

class NoteRepositoryImpl(private val noteService: NoteService) : NoteRepository {
  override suspend fun getAllNotes(email: String): BaseResponse<Any> {
    return BaseResponse.SuccessResponse(data = noteService.getAllNotes(email), message = "Success")
  }

  override suspend fun addNote(note: Note, email: String): BaseResponse<Any> {
    val noteID = noteService.addNote(note, email)
    val resNote =
      Note(id = noteID, noteDescription = note.noteDescription, noteTitle = note.noteTitle, date = note.date)
    return BaseResponse.SuccessResponse(data = resNote, message = "Note Added Successfully")
  }

  override suspend fun updateNote(note: Note, id: String): BaseResponse<Any> {
    noteService.updateNote(note, id)
    return BaseResponse.SuccessResponse(data = note, message = "Successful Update ")

  }


}
