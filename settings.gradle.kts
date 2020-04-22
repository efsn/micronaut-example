rootProject.name = "micronaut-example"

include("micronaut-service")
include("micronaut-function-ali")
include("micronaut-function-aws")
include("micronaut-function-aws-gateway")
include("micronaut-cli")

//files("x").forEach { dir ->
//    dir.listFiles().forEach {
//        if (it.isDirectory) {
//            include(it.name)
//            project(":${it.name}").projectDir = it
//        }
//    }
//}