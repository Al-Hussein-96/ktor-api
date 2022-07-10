package com.alhussein.routes

import com.alhussein.dao.dao
import com.alhussein.models.User
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlin.text.get

fun Route.userRouting() {
    route("/users") {
//        get {
//            call.respond(FreeMarkerContent("index.ftl", mapOf("users" to dao.allUsers())))
//        }
        get {
//            call.respondText("No users11 found", status = HttpStatusCode.OK)

            if (dao.allUsers().isNotEmpty()) {
                println("All users not empty")
                call.respond(dao.allUsers())
//                call.respondText("No users found", status = HttpStatusCode.OK)
            } else {
                call.respondText("No users11 found", status = HttpStatusCode.OK)
            }
        }
        get("{id?}") {
            val id = call.parameters["id"]?.toInt() ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val user =
                dao.user(id) ?: return@get call.respondText(
                    "No customer with id $id",
                    status = HttpStatusCode.NotFound
                )
            call.respond(user)
        }
        post {
            val user = call.receive<User>()
            dao.addNewUser(user)
            call.respondText("Customer stored correctly", status = HttpStatusCode.Created)
        }
        delete("{id?}") {

        }
    }
}