val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val postgresql_version: String by project

plugins {
  kotlin("jvm") version "1.9.23"
  id("io.ktor.plugin") version "2.3.10"
  kotlin("plugin.serialization") version "1.4.10"

}

group = "com.example"
version = "0.0.1"

application {
  mainClass.set("io.ktor.server.netty.EngineMain")

  val isDevelopment: Boolean = project.ext.has("development")
  applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
  mavenCentral()
}

dependencies {
  val exposed_version = "0.49.0"
  val postgres_version = "42.7.3"
  val mysqlVersion = "8.0.33"
  val koinKtor = "3.4.1"
  val hikaricpVersion = "5.0.1"

  implementation("io.ktor:ktor-server-core-jvm")
  implementation("io.ktor:ktor-server-content-negotiation-jvm")
  implementation("io.ktor:ktor-server-auth-jvm")
  implementation("io.ktor:ktor-server-auth-jwt-jvm")
  implementation("io.ktor:ktor-server-sessions-jvm")
  implementation("io.ktor:ktor-server-netty-jvm")
  implementation("ch.qos.logback:logback-classic:$logback_version")
  testImplementation("io.ktor:ktor-server-tests-jvm")
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
  implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
  implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
  implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
  implementation("org.postgresql:postgresql:$postgres_version")
  implementation ("com.beust:klaxon:5.5")


  // Usingb MySql
  implementation("mysql:mysql-connector-java:$mysqlVersion")
  // Koin for Ktor
  implementation("io.insert-koin:koin-ktor:$koinKtor")
  //connection pooling
  implementation("com.zaxxer:HikariCP:$hikaricpVersion")

  implementation ("io.ktor:ktor-serialization-jackson:$ktor_version")

}
