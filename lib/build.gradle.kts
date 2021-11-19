import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    `java-library`
    `maven-publish`
    signing
    kotlin(Kotlin.jvmId)
    kotlin(Kotlin.kaptId)
    dokka(Kotlin.dokkaId) version Kotlin.version
}

description = "The ComboBox model for the FlatLaf themes"
group = "io.github.kolod"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(Kotlin.stdlibJdk8)
    implementation(FlatLookAndFeel.core)
    implementation(FlatLookAndFeel.extras)
    implementation(FlatLookAndFeel.intellij)
    implementation(Slf4j.core)
    dokkaPlugin(Kotlin.dokka)
}

java.withSourcesJar()

tasks.withType<DokkaTask>().configureEach {
    dokkaSourceSets {
        named("main") {
            moduleName.set("Flat Look and Feel Themes ComboBox Model")
        }
    }
}

val dokkaJavadocJar by tasks.register<Jar>("dokkaJavadocJar") {
    dependsOn(tasks.dokkaJavadoc)
    from(tasks.dokkaJavadoc.flatMap { it.outputDirectory })
    archiveClassifier.set("javadoc")
}

val dokkaHtmlJar by tasks.register<Jar>("dokkaHtmlJar") {
    dependsOn(tasks.dokkaHtml)
    from(tasks.dokkaHtml.flatMap { it.outputDirectory })
    archiveClassifier.set("html-doc")
}

publishing {
    repositories {
        maven {
            val releasesRepoUrl = "https://s01.oss.sonatype.org/content/repositories/releases/"
            val snapshotsRepoUrl = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
            val ossrhUsername: String by project
            val ossrhPassword: String by project

            url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
            credentials {
                username = ossrhUsername
                password = ossrhPassword
            }
        }
    }
    publications {
        create<MavenPublication>("library") {
            from(components["java"])
            version = "1.1.1"
            groupId = "io.github.kolod"
            artifactId = "flatlaf-themes-combobox-model"
            artifact(dokkaJavadocJar)
            artifact(dokkaHtmlJar)
        }
        create<MavenPublication>("mavenJava") {
            pom {
                name.set("flatlaf-themes-combobox-model")
                description.set("The ComboBox model for the FlatLaf themes.")
                url.set("https://github.com/kolod/flatlaf-themes-combobox-model")
                licenses {
                    license {
                        name.set("The MIT License")
                        url.set("https://raw.githubusercontent.com/kolod/flatlaf-themes-combobox-model/main/LICENSE")
                    }
                }
                developers {
                    developer {
                        id.set("Kolod")
                        name.set("Oleksandr Kolodkin")
                        email.set("alexandr.kolodkin@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/kolod/flatlaf-themes-combobox-model.git")
                    developerConnection.set("scm:git:ssh:git@github.com:kolod/flatlaf-themes-combobox-model.git")
                    url.set("https://github.com/kolod/flatlaf-themes-combobox-model")
                }
            }
        }
    }
}

signing {
    sign(publishing.publications["mavenJava"])
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = Jvm.version
}
