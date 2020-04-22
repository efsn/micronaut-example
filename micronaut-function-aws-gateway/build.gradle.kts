dependencies {
    developmentOnly("io.micronaut:micronaut-http-server-netty:2.0.0.M2")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut.aws:micronaut-function-aws-api-proxy") {
        exclude(group = "org.apache.httpcomponents")
        exclude(group = "org.slf4j")
    }

    runtimeOnly("org.apache.logging.log4j:log4j-core:2.8.2")
    runtimeOnly("org.apache.logging.log4j:log4j-api:2.8.2")
    runtimeOnly("org.apache.logging.log4j:log4j-slf4j-impl:2.8.2")
    runtimeOnly("com.amazonaws:aws-lambda-java-log4j2:1.1.0")
}

//mainClassName = "first.app.Application"

tasks {
    register<Zip>("buildZip") {
        from(sourceSets.main)
        into("lib") {
            from(configurations.compileClasspath)
            from(configurations.runtimeClasspath)
        }
    }

    shadowJar {
        dependsOn("buildZip")
    }
}