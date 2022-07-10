package com.alhussein.dao

import com.alhussein.models.Users
import io.ktor.server.config.*
import kotlinx.coroutines.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*
import org.jetbrains.exposed.sql.transactions.experimental.*

object DatabaseFactory {
    fun init(config: ApplicationConfig) {

//        val driverClassName = config.property("ktor.database.driverClassName").getString()
//        val jdbcURL = config.property("ktor.database.jdbcURL").getString()
////        val username = config.property("ktor.database.user").getString()
//        val username = "postgres"
//        val password = config.property("ktor.database.password").getString()
//        val defaultDatabase = config.property("ktor.database.database").getString()

//        val driverClassName = config.property("storage.driverClassName").getString()
//        val jdbcURL = config.property("storage.jdbcURL").getString()
//        val database = Database.connect(jdbcURL, driverClassName)

        val driverClassName = "org.h2.Driver"
        val jdbcURL = "jdbc:h2:file:./build/db"
        val database = Database.connect(jdbcURL, driverClassName)


//        val database = Database.connect(
//            url = "$jdbcURL/$defaultDatabase",
//            driver = driverClassName,
//            user = username,
//            password = password
//        )

        transaction(database) {
            SchemaUtils.create(Users)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}
