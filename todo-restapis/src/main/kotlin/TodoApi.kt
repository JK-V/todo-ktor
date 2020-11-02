package com.jkv

import TodoItem
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import java.time.LocalDate

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

val todo1 = TodoItem(
    "ToDo1: Add Data access",
    "Add DB support",
    LocalDate.of(2020, 10, 31),
    "Me",
    Importance.HIGH
)

val todo2 = TodoItem(
    "ToDo2: Add Data service",
    "Add DB support",
    LocalDate.of(2020, 11, 1),
    "Me2",
    Importance.LOW
)

val todos = listOf(
    todo1,
    todo2
)