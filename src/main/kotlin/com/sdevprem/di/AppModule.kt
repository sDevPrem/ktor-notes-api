package com.sdevprem.di

import com.sdevprem.data.db.DBHelper
import com.sdevprem.data.repository.NotesRepository
import com.sdevprem.data.repository.UserRepository
import com.sdevprem.data.service.NotesService
import com.sdevprem.data.service.UserService
import org.koin.dsl.module

val appModule = module {
    single { DBHelper.database }

    single { NotesRepository(get()) }
    single { NotesService(get()) }

    single { UserRepository(get()) }
    single { UserService(get()) }
}