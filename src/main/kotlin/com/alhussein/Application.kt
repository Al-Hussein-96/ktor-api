package com.alhussein


import com.alhussein.dao.DatabaseFactory
import com.alhussein.plugins.configureRouting
import com.alhussein.plugins.configureTemplating
import io.ktor.server.application.*


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    DatabaseFactory.init(environment.config)
    configureRouting()
    configureTemplating()
}
