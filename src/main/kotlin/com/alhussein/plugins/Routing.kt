package com.alhussein.plugins


import com.alhussein.dao.dao
import com.alhussein.routes.userRouting
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Application.configureRouting() {
    routing {
        static("/static") {
            resources("files")
        }
        get("/") {
            call.respondRedirect("users")
        }

        userRouting()



    }
}


/*
*             get {
                call.respond(FreeMarkerContent("index.ftl", mapOf("users" to dao.allUsers())))
            }
            get("new") {
                call.respond(FreeMarkerContent("new.ftl", model = null))
            }
            post {
                val formParameters = call.receiveParameters()
                val title = formParameters.getOrFail("name")
                val status = formParameters.getOrFail<Int>("status")
                val user = dao.addNewUser(title, status)
                call.respondRedirect("/users/${user?.id}")
            }
            get("{id}") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                call.respond(FreeMarkerContent("show.ftl", mapOf("user" to dao.user(id))))
            }
            get("{id}/edit") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                call.respond(FreeMarkerContent("edit.ftl", mapOf("user" to dao.user(id))))
            }
            post("{id}") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                val formParameters = call.receiveParameters()
                when (formParameters.getOrFail("_action")) {
                    "update" -> {
                        val name = formParameters.getOrFail("name")
                        val status = formParameters.getOrFail<Int>("status")
                        dao.editUser(id, name, status)
                        call.respondRedirect("/articles/$id")
                    }
                    "delete" -> {
                        dao.deleteUser(id)
                        call.respondRedirect("/articles")
                    }
                }
            }*/