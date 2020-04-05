dependencies {
    implementation("info.picocli:picocli")
    implementation("io.micronaut.configuration:micronaut-picocli")

    kapt("io.micronaut.configuration:micronaut-picocli")
}

application {
    mainClassName = "demo.cli.kt.DemoCliKtCommand"
}

tasks {
    jar {
        manifest {
            attributes["Main-Class"] = application.mainClassName
        }
    }
}