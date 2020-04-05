plugins {
    id("jp.classmethod.aws.lambda") version "0.39"
}

dependencies {
    implementation("io.micronaut:micronaut-function-aws")

    runtimeOnly("com.amazonaws:aws-lambda-java-log4j2:1.0.0")
    runtimeOnly("org.apache.logging.log4j:log4j-slf4j-impl:2.9.1")
}

application {
    mainClassName = "io.micronaut.function.executor.FunctionApplication"
}

tasks {
    shadowJar {
        mergeServiceFiles()
//        transform(com.github.jengelman.gradle.plugins.shadow.transformers.Log4j2PluginsCacheFileTransformer)
    }

    jar {
        manifest {
            attributes["Main-Class"] = application.mainClassName
        }
    }
}

//if (new File ("${System.getProperty("user.home")}/.aws/credentials").exists()) {
//    task deploy (type: jp.classmethod.aws.gradle.lambda.AWSLambdaMigrateFunctionTask, dependsOn: shadowJar) {
//        functionName = "faas-demo-kt"
//        handler = "io.micronaut.function.executor.FunctionApplication::apply"
//        role = "arn:aws:iam::${aws.accountId}:role/lambda_basic_execution"
//        runtime = com.amazonaws.services.lambda.model.Runtime.Java8
//        zipFile = shadowJar.archivePath
//        memorySize = 192
//        timeout = 60
//    }
//}
