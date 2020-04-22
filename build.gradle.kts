plugins {
    val kotlinVersion = "1.3.72"

    application
    kotlin("jvm") version kotlinVersion
    kotlin("kapt") version kotlinVersion
    kotlin("plugin.allopen") version kotlinVersion

    id("com.github.johnrengelman.shadow") version "5.2.0"
}

allprojects {
    apply(plugin = "application")
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "kotlin-allopen")
    apply(plugin = "com.github.johnrengelman.shadow")
    apply(from = rootProject.file("gradle/ktlint.gradle.kts"))

    repositories {
        mavenCentral()
        jcenter()
    }

    configurations.create("developmentOnly")

    dependencies {
        implementation(platform("io.micronaut:micronaut-bom:2.0.0.M2"))

        implementation(kotlin("stdlib-jdk8"))
        implementation(kotlin("reflect"))

//        developmentOnly("io.micronaut:micronaut-http-server-netty")

        implementation("io.micronaut:micronaut-runtime")
        implementation("io.micronaut.reactor:micronaut-reactor")
        implementation("io.micronaut:micronaut-validation")
        implementation("io.micronaut.reactor:micronaut-reactor-http-client:1.0.0.RC1")

        kapt(platform("io.micronaut:micronaut-bom:2.0.0.M2"))
        kapt("io.micronaut:micronaut-inject-java")
        kapt("io.micronaut:micronaut-validation")
//        kapt("io.micronaut.configuration:micronaut-hibernate-validator")
        kapt("io.micronaut.configuration:micronaut-openapi")

        runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
        runtimeOnly("ch.qos.logback:logback-classic:1.2.3")

        kaptTest(platform("io.micronaut:micronaut-bom:2.0.0.M2"))
        kaptTest("io.micronaut:micronaut-inject-java")

        testImplementation("org.junit.jupiter:junit-jupiter-engine")
        testImplementation("org.junit.jupiter:junit-jupiter-api")
        testImplementation("org.jetbrains.spek:spek-junit-platform-engine:1.1.5")

        testImplementation("io.kotlintest:kotlintest-runner-junit5:3.3.2")

        testImplementation("io.micronaut.test:micronaut-test-junit5")
        testImplementation("io.micronaut.test:micronaut-test-kotlintest")
        testImplementation("org.mockito:mockito-junit-jupiter:2.22.0")
        testImplementation("io.mockk:mockk:1.9.3")
    }

    tasks {
        compileKotlin {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
                freeCompilerArgs = listOf("-Xjsr305=strict")
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
            classpath += configurations.getByName("developmentOnly")
        }

        shadowJar {
            mergeServiceFiles()
        }

        withType<JavaExec> {
            classpath += configurations.getByName("developmentOnly")
            jvmArgs("-noverify", "-XX:TieredStopAtLevel=1", "-Dcom.sun.management.jmxremote")
        }
    }

    allOpen {
        annotation("io.micronaut.aop.Around")
    }
}