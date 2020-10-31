package com.jkv

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.deser.Deserializers
import com.fasterxml.jackson.databind.deser.std.DateDeserializers
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import java.time.LocalDate

data class TodoItem(
    val title: String,
    val details: String,
    @JsonSerialize(using = ToStringSerializer::class)
    @JsonDeserialize(using = DateDeserializers.DateDeserializer::class)
    val dueDate: LocalDate,
    val assignedTo: String,
    val importance: Int
)

val todo1 = TodoItem(
    "ToDo1: Add Data access",
    "Add DB support",
    LocalDate.of(2020, 10, 31),
    "Me",
    1
)

val todo2 = TodoItem(
    "ToDo2: Add Data service",
    "Add DB support",
    LocalDate.of(2020, 11, 1),
    "Me2",
    1
)