package com.jkv

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.todoApi(){
    route("/api/todo"){
        get("/"){
            call.respond(todos)
        }
        get("/2"){
            call.respond("I'm inside todos")
        }
    }
}