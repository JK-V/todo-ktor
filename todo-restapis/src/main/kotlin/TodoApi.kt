package com.jkv

import TodoItem
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import java.time.LocalDate

//Routing Structured handling of HTTP requests
/**
 * route("a) {
 *  route("b) {
 *      get { .. }
 *      post{ .. }
 *    }
 *  }
 */
fun Routing.todoApi() {
    route("/api") {
//        header("Accpet", "application/json/vendor.todoapi.v1+json") {
//            get("/todos") {
//                call.respond(todos)
//            }
//        }

        //intead of above header method
        accept(todoContentV1){
            get("/todos") {
                call.respond(todos)
            }
        }

        route("/todos", HttpMethod.Get) {
            handle {
                call.respond(todos)
            }
        }

        get("/todos/{id}") {
            val id = call.parameters["id"]!!
            if (id == null || id == "" || id == "null") {
                return@get
            }
            try {
                val todo = todos[id.toInt()]
                call.respond(todo)
            } catch (e: Throwable) {
                call.respond(HttpStatusCode.NotFound)
            }
        }

        post("todos") {
            val todo = call.receive<TodoItem>()
            val newTodo =
//                TodoItem(todos.size +1, todo.title, todo.details, LocalDate.of(2020, 10, 31),
//                    todo.assignedTo, todo.importance)
                TodoItem(
                    todos.size + 1, todo.title, todo.details,
                    todo.assignedTo, todo.importance
                )
            todos = todos + newTodo
            call.respond(HttpStatusCode.Created, todos)
        }

        put("todos/{id}") {
            val id = call.parameters["id"]
            if (id == null || id == "" || id == "null") {
                return@put
            }

            val foundItem = todos.getOrNull(id.toInt())
            if (foundItem == null) {
                call.respond(HttpStatusCode.NotFound)
                return@put
            } else {
                val todoItem = call.receive<TodoItem>()
                todos = todos.filter { it.id != todoItem.id }
                todos += todoItem

                call.respond(HttpStatusCode.NoContent)
            }
        }

        delete("todos/{id}") {
            val id = call.parameters["id"]
            if (id == null || id == "" || id == "null") {
                return@delete
            }

            val foundItem = todos.getOrNull(id.toInt())
            if (foundItem == null) {
                call.respond(HttpStatusCode.NotFound)
                return@delete
            } else {
                todos = todos.filter { it.id != id.toInt() }
                call.respond(HttpStatusCode.NoContent)
            }
        }
    }
}

val todo1 = TodoItem(
    1,
    "ToDo1: Add Data access",
    "Add DB support",
//    LocalDate.of(2020, 10, 31),
    "Me",
    "HIGH"
)

val todo2 = TodoItem(
    2,
    "ToDo2: Add Data service",
    "Add DB support",
//    LocalDate.of(2020, 11, 1),
    "Me2",
    "LOW"
)

var todos = listOf(
    todo1,
    todo2
)