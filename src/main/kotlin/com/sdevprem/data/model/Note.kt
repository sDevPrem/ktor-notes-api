package com.sdevprem.data.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.ktorm.entity.Entity

interface Note : Entity<Note> {
    val id: Int
    val title: String
    val description: String

    @get:JsonIgnore
    val user: User
}
