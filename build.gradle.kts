plugins {
    `java-library`
    id("org.cadixdev.licenser") version "0.6.1"
    `maven-publish`
    id("com.github.ben-manes.versions") version "0.39.0"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("net.kyori.indra") version "2.1.0"
    id("net.kyori.indra.publishing.sonatype") version "2.1.0"
    id("org.spongepowered.gradle.sponge.dev") version "1.1.1"
    id("org.checkerframework") version "0.6.8"

}

repositories {
    mavenCentral()
}

val organization: String by project
val projectUrl: String by project
val junitVersion: String by project
val mockitoVersion: String by project

license {
    properties {
        this["name"] = "collections"
        this["organization"] = organization
        this["url"] = projectUrl
    }
    header(rootProject.file("HEADER.txt"))

    include("**/*.java")
    newLine(false)
}

dependencies {
    // fastutil
    implementation("it.unimi.dsi:fastutil-core:8.5.8")

    // Tests
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")

    testImplementation("org.mockito:mockito-core:$mockitoVersion")
    testImplementation("org.mockito:mockito-junit-jupiter:$mockitoVersion")
    testImplementation("org.mockito:mockito-inline:$mockitoVersion")
}

checkerFramework {
//    checkers = listOf(
//        "org.checkerframework.checker.nullness.NullnessChecker",
//        "org.checkerframework.checker.units.UnitsChecker"
//    )
}

tasks {
    shadowJar {
        minimize()
        relocate("it.unimi.dsi.fastutil", "org.spongepowered.collections.libs.fastutil")
    }
}

spongeConvention {
    repository("collections") {
        ci(true)
        publishing(true)
    }
    mitLicense()
}

indra {
    javaVersions() {
        target(8)
        testWith(8,11,17)
        minimumToolchain(17)
    }
    configurePublications {
        pom {
            developers {
                developer {
                    id.set("gabizou")
                    name.set("Gabriel Harris-Rouquette")
                    email.set("gabizou@spongepowered.org")
                }
                developer {
                    id.set("Deamon")
                    name.set("Nick Conde")
                    email.set("theedeamon@gmail.com")
                }
            }
        }
    }
}