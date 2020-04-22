dependencies {
//    implementation("io.micronaut.")
    developmentOnly("io.micronaut:micronaut-http-server-netty")
    developmentOnly("io.micronaut:micronaut-function-web")
}

application {
    mainClassName = "cn.elmi.example.micronaut.Application"
}