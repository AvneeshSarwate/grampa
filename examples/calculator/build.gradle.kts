group = "com.github.mpe85"

plugins {
    kotlin("jvm") version "1.9.24"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.mpe85:grampa:1.3.1")
}

application {
    mainClass.set("com.github.mpe85.calculator.Main")
}
