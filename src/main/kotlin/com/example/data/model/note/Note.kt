package com.example.data.model.note

import com.example.data.table.notetable.NoteTable.bool

data class Note(
  val id: Int,
  val noteTitle: String,
  val noteDescription: String,
  val date: Long,
  val isChecked: Boolean = false
)
