plugins {
    val kotlinVersion = "1.3.71"

//    `java-library`
    application
    kotlin("jvm") version kotlinVersion
    kotlin("kapt") version kotlinVersion
    kotlin("plugin.allopen") version kotlinVersion

    id("com.github.johnrengelman.shadow") version "5.0.0"

}

subprojects {
    apply(plugin = "application")
//    apply(plugin = "java-library")
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "kotlin-allopen")
    apply(plugin = "com.github.johnrengelman.shadow")
    apply(from = rootProject.file("gradle/ktlint.gradle.kts"))

    repositories {
        mavenCentral()
        jcenter()
    }

    dependencies {
        implementation(platform("io.micronaut:micronaut-bom:2.0.0.M2"))

        implementation(kotlin("stdlib-jdk8"))
        implementation(kotlin("reflect"))

        implementation("io.micronaut:micronaut-runtime")
        implementation("io.micronaut:micronaut-http-server-netty")
        implementation("io.micronaut:micronaut-http-client")

        kapt(platform("io.micronaut:micronaut-bom:2.0.0.M2"))
        kapt("io.micronaut:micronaut-inject-java")
        kapt("io.micronaut:micronaut-validation")
        kapt("io.micronaut.configuration:micronaut-openapi")

        runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
        runtimeOnly("ch.qos.logback:logback-classic:1.2.3")

        kaptTest(platform("io.micronaut:micronaut-bom:2.0.0.M2"))
        kaptTest("io.micronaut:micronaut-inject-java")

        testImplementation("org.junit.jupiter:junit-jupiter-engine")
        testImplementation("org.junit.jupiter:junit-jupiter-api")
        testImplementation("org.jetbrains.spek:spek-junit-platform-engine:1.1.5")

        testImplementation("io.micronaut.test:micronaut-test-junit5")
        testImplementation("org.mockito:mockito-junit-jupiter:2.22.0")

    }

    tasks {
        compileKotlin {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
                freeCompilerArgs = listOf("-Xjsr305=strict")
                // retain parameter names for java reflection
                javaParameters = true
            }
        }

        compileTestKotlin {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
                javaParameters = true
            }
        }

        test {
            failFast = true
            useJUnitPlatform()
            testLogging {
                events("passed", "skipped", "failed")
            }
        }

        shadowJar {
            mergeServiceFiles()
        }
    }

    allOpen {
        annotation("io.micronaut.aop.Around")
    }

}