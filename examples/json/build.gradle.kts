group = "com.github.mpe85"

plugins {
    kotlin("jvm") version "1.7.10"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8", "1.7.10"))
    implementation("com.github.mpe85:grampa:1.1.0")
}

application {
    mainClass.set("com.github.mpe85.json.Main")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}
