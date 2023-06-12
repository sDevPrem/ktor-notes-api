package com.sdevprem.data.dao

import com.sdevprem.data.model.Note
import com.sdevprem.data.schema.NoteSchema
import org.ktorm.database.Database
import org.ktorm.dsl.*

class NotesDao(
    private val db: Database
) {

    fun getNotes(uid: Int) = db
        .from(NoteSchema)
        .select()
        .where {
            NoteSchema.uid eq uid
        }
        .map { NoteSchema.createEntity(it) }

    fun getNotesById(id: Int) = db
        .from(NoteSchema)
        .select()
        .where {
            NoteSchema.id eq id
        }
        .map { NoteSchema.createEntity(it) }
        .firstOrNull()

    fun insertNote(uid: Int, note: Note) =
        db
            .insertAndGenerateKey(NoteSchema) {
                set(NoteSchema.uid, uid)
                set(NoteSchema.description, note.description)
                set(NoteSchema.title, note.title)
            } as Int

    fun updateNote(id: Int, note: Note) =
        db
            .update(NoteSchema) {
                set(NoteSchema.description, note.description)
                set(NoteSchema.title, note.title)
                where { NoteSchema.id eq id }
            }

    fun deleteNote(id: Int) =
        db.delete(NoteSchema) { NoteSchema.id eq id }
}