plugins {
    kotlin("jvm") version "2.0.20"
    kotlin("plugin.noarg") version "2.0.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.hibernate:hibernate-core:6.6.0.Final")

    implementation("mysql:mysql-connector-java:8.0.33")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(20)
}