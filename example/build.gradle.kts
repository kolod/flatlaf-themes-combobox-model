import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin(Kotlin.jvmId)
    kotlin(Kotlin.kaptId)
    shadow(Shadow.id) version Shadow.version
}

project.ext {
    set("mainClassName", "io.github.kolod.Example")
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(Kotlin.stdlibJdk8)
    implementation(Log4j.core)
    implementation(Log4j.api)
    implementation(Log4j.slf4j)
    implementation(Kolod.FlatLookAndFeelfModel.core)
}

application {
    mainClass.set(project.ext["mainClassName"] as String)
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = Jvm.version
}