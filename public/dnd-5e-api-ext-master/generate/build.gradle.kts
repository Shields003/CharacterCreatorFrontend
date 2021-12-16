plugins {
    kotlin("jvm")
    application
}

repositories {
    // Use jcenter for resolving dependencies.
    // You can declare any Maven/Ivy/file repository here.
    mavenCentral()
    jcenter()
}


application {
    this.applicationDefaultJvmArgs += "-Dapi.resource.directory=${project.rootDir.toPath()}/api-resources"
    this.applicationDefaultJvmArgs += "-Dgenerated.target.directory=${project.rootDir.toPath()}/web/src/main/kotlin"

    mainClassName = "dandd.character.automation.AppKt"
}
dependencies {
    implementation(project(":core"))
}
