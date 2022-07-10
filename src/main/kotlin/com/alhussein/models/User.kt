package com.alhussein.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*

@Serializable
data class User(val id: Int, val name: String, val status: Int)


object Users : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 128)
    val status = integer("status")

    override val primaryKey = PrimaryKey(id)
}
