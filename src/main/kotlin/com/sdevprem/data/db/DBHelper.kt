package com.sdevprem.data.db

import io.ktor.server.application.*
import org.ktorm.database.Database

object DBHelper {
    private var dbUrl = ""//"jdbc:mysql://127.0.0.1:3306/demo"
    private var dbUser = ""//"root"
    private var dbPwd = ""//"password"

    fun Application.configureDbVariable() {
        dbUrl = environment.config.propertyOrNull("db.config.db_url")?.getString() ?: ""
        dbUser = environment.config.propertyOrNull("db.config.db_user")?.getString() ?: ""
        dbPwd = environment.config.propertyOrNull("db.config.db_pwd")?.getString() ?: ""
    }

    val database by lazy {
        Database.connect(
            url = dbUrl,
            user = dbUser,
            password = dbPwd,
            driver = "com.mysql.cj.jdbc.Driver",
        )
    }
}