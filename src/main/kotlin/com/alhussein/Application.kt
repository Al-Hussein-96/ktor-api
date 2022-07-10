package com.alhussein


import io.ktor.server.application.*
import com.alhussein.plugins.*
import com.alhussein.dao.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    DatabaseFactory.init(environment.config)
    configureSerialization()

    configureRouting()

//    configureTemplating()
}
