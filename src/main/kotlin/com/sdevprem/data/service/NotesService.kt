package com.sdevprem.data.service

import com.sdevprem.data.model.Note
import com.sdevprem.data.repository.NotesRepository

class NotesService(
    private val notesRepository: NotesRepository
) {
    fun getNotes(uid: Int) =
        notesRepository.getNotes(uid)

    fun deleteNote(uid: Int, noteId: Int) =
        notesRepository.deleteNote(uid, noteId)

    fun updateNote(uid: Int, noteId: Int, note: Note) =
        notesRepository.updateNote(uid, noteId, note)

    fun createNote(uid: Int, note: Note) =
        notesRepository.insertNote(uid, note)

    fun getNoteById(uid: Int, noteId: Int) =
        notesRepository.getNotesById(uid, noteId)
}