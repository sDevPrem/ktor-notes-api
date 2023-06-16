package com.sdevprem.di

import com.sdevprem.data.DBHelper
import com.sdevprem.data.repository.NotesRepository
import com.sdevprem.data.service.NotesService
import org.koin.dsl.module

val appModule = module {
    single { DBHelper.database }
    single { NotesRepository(get()) }
    single { NotesService(get()) }
}