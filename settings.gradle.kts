rootProject.name = "grampa"

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

include(":examples:calculator", ":examples:json")
