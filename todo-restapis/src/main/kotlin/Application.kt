package com.jkv

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.content.*
import io.ktor.features.*
import io.ktor.http.content.*
import io.ktor.jackson.*

val todos = listOf(todo1, todo2)

fun main(args: Array<String>): Unit = io.ktor.server.cio.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    //Install routing as a feature
    install(Routing) {
        //This is DSL
        todoApi()
    }

    //To handle errors gracefully
    install(StatusPages) {
        //You can specify what to show in event of errors like 500, 404
        this.exception<Throwable> { e ->
            call.respondText(e.localizedMessage, ContentType.Text.Plain)
            throw e
        }
    }

    //Which representations to use like xml, JSON
    install(ContentNegotiation) {
        //Incoming json convert it to ktor types,
        //Convert Outgoing types to json
        jackson {
            //To make it human readable
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }
}

