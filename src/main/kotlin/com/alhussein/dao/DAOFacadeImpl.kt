package com.alhussein.dao


import com.alhussein.dao.DatabaseFactory.dbQuery
import com.alhussein.models.User
import com.alhussein.models.Users
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import org.jetbrains.exposed.sql.*

class DAOFacadeImpl : DAOFacade {
    private fun resultRowToArticle(row: ResultRow) = User(
        id = row[Users.id],
        name = row[Users.name],
        status = row[Users.status],
    )

    override suspend fun allUsers(): List<User> = dbQuery {
        Users.selectAll().map(::resultRowToArticle)
    }

    override suspend fun user(id: Int): User? = dbQuery {
        Users
            .select { Users.id eq id }
            .map(::resultRowToArticle)
            .singleOrNull()
    }

    override suspend fun addNewUser(user: User): User? = dbQuery {
        val insertStatement = Users.insert {
            it[name] = user.name
            it[status] = user.status
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToArticle)
    }

    override suspend fun editUser(id: Int, name: String, status: Int): Boolean = dbQuery {
        Users.update({ Users.id eq id }) {
            it[Users.name] = name
            it[Users.status] = status
        } > 0
    }

    override suspend fun deleteUser(id: Int): Boolean = dbQuery {
        Users.deleteWhere { Users.id eq id } > 0
    }
}

val dao: DAOFacade = DAOFacadeImpl().apply {
    runBlocking {
        if (allUsers().isEmpty()) {
            addNewUser(User(id = 1, name = "Mohammad", status = 1))
        }
    }
}
