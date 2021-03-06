package com.jkv

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.features.*
import io.ktor.jackson.*

fun main(args: Array<String>): Unit = io.ktor.server.cio.EngineMain.main(args)

//
val todoContentV1 = ContentType("application", "vendor.todoapi.v1+json")

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    //This is DSL
    //Install routing as a feature
    install(Routing) {
        //Useful for more complex routing applications
        trace { application.log.trace(it.buildText()) }
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
        //Register with content negotiation with Jackson feature
//        register(todoContentV1, JacksonConverter())
        //Another way to register
        jackson(todoContentV1) {
            enable(SerializationFeature.INDENT_OUTPUT)
            disableDefaultTyping()
        }
    }
}

