/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    `java-library`
    `maven-publish`
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    api(libs.org.jetbrains.annotations)
    api(libs.org.xerial.sqlite.jdbc)
    api(libs.org.apache.logging.log4j.log4j)
    api(libs.org.flywaydb.flyway.maven.plugin)
    testImplementation(libs.org.junit.jupiter.junit.jupiter)
    testImplementation(libs.org.apache.logging.log4j.log4j.slf4j.impl)
    testImplementation(libs.org.slf4j.slf4j.nop)
}

group = "org.example"
version = "1.0-SNAPSHOT"
description = "textsearchenvironment"
java.sourceCompatibility = JavaVersion.VERSION_1_8

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}
