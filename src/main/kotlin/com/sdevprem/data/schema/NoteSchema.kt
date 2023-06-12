package com.sdevprem.data.schema

import com.sdevprem.data.model.Note
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.text
import org.ktorm.schema.varchar

object NoteSchema : Table<Note>("t_note") {
    val id = int("id").primaryKey().bindTo { it.id }
    val title = varchar("title").bindTo { it.title }
    val description = text("description").bindTo { it.description }
    val uid = int("uid").references(UserSchema) { it.user }
}