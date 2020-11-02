import org.jetbrains.kotlin.gradle.dsl.Coroutines
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktor_version: String by project

plugins {
    "java-library"
}

dependencies {
    implementation("io.ktor:ktor-jackson:$ktor_version")
}