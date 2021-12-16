plugins {
    kotlin("js")
}


repositories {
    // Use jcenter for resolving dependencies.
    // You can declare any Maven/Ivy/file repository here.

    //transitive dependencies for kvision
    maven {
        url = uri("https://dl.bintray.com/gbaldeck/kotlin")
        metadataSources {
            mavenPom()
            artifact()
        }
    }

    maven("https://kotlin.bintray.com/kotlin-js-wrappers/")
    maven("https://dl.bintray.com/rjaros/kotlin")
    mavenCentral()
    jcenter()
}

kotlin{
    target.browser {
        dceTask {
            keep("ktor-ktor-io.\$\$importsForInline\$\$.ktor-ktor-io.io.ktor.utils.io")
        }
        testTask {
            enabled = false
            useKarma {
                useChromeHeadless()
            }
        }
    }
}

val ktor_version = "1.3.1"
val kotlinWrapperVersion = "16.13.1-pre.110-kotlin-1.3.72"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-js")
    testImplementation("org.jetbrains.kotlin:kotlin-test-js")
    implementation("io.ktor:ktor-client-js:$ktor_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.3.8")

    implementation("org.jetbrains:kotlin-react:$kotlinWrapperVersion")
    implementation("org.jetbrains:kotlin-react-dom:$kotlinWrapperVersion")
    implementation(npm("react", "16.13.1"))
    implementation(npm("react-dom", "16.13.1"))

    //Kotlin Styled (chapter 3)
    implementation("org.jetbrains:kotlin-styled:1.0.0-pre.94-kotlin-1.3.70")

    implementation(npm("styled-components"))
    implementation(npm("inline-style-prefixer"))
    implementation(npm("style-loader"))
    implementation(npm("css-loader"))

    implementation(npm("text-encoding"))
    implementation(npm("bufferutil"))
    implementation(npm("utf-8-validate"))
    implementation(npm("abort-controller"))
    implementation(npm("fs"))

    implementation(npm("jquery", "3.5.1"))
    implementation(npm("popper.js", "1.16.0"))
    implementation(npm("bootstrap", "4.5.0"))
}









