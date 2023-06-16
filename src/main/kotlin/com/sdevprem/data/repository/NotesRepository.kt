package com.sdevprem.data.repository

import com.sdevprem.data.model.Note
import com.sdevprem.data.schema.NoteSchema
import org.ktorm.database.Database
import org.ktorm.dsl.*

class NotesRepository(
    private val db: Database
) {

    fun getNotes(uid: Int) = db
        .from(NoteSchema)
        .select()
        .where {
            NoteSchema.uid eq uid
        }
        .map { NoteSchema.createEntity(it) }

    fun getNotesById(uid: Int, id: Int) = db
        .from(NoteSchema)
        .select()
        .where {
            (NoteSchema.uid eq uid) and
                    (NoteSchema.id eq id)
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

    fun updateNote(uid: Int, id: Int, note: Note) =
        db
            .update(NoteSchema) {
                set(NoteSchema.description, note.description)
                set(NoteSchema.title, note.title)
                where {
                    (NoteSchema.uid eq uid) and
                            (NoteSchema.id eq id)
                }
            }

    fun deleteNote(uid: Int, id: Int) =
        db.delete(NoteSchema)
        {
            (NoteSchema.uid eq uid) and
                    (NoteSchema.id eq id)
        }
}