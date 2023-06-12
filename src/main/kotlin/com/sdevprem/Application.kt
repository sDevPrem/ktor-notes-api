package com.sdevprem

import com.sdevprem.data.DBHelper.configureDbVariable
import com.sdevprem.plugins.*
import com.sdevprem.route.configureNotesRoute
import com.sdevprem.route.registerAuthRoute
import io.ktor.server.application.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureSerialization()
    configureMonitoring()
    configureSecurity()
    configureDbVariable()
    configureRouting()
    configureNotesRoute()
    registerAuthRoute()
}
