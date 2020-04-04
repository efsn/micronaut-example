rootProject.name = "micronaut-example"
rootProject.buildFileName = "build.gradle.kts"

include("mn-server")

//files("x").forEach { dir ->
//    dir.listFiles().forEach {
//        if (it.isDirectory) {
//            include(it.name)
//            project(":${it.name}").projectDir = it
//        }
//    }
//}