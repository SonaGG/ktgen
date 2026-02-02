allprojects {
    group = "net.echonolix"
    version = "1.0.0"
}

plugins {
    `kotlin-dsl`
    `maven-publish`
}

subprojects {
    apply {
        plugin("java")
        plugin("kotlin")
    }
}

allprojects {
    apply {
        plugin("maven-publish")
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        api(kotlin("reflect"))
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(8))
        }
    }

    publishing {
        repositories {
            maven {
                url = uri("https://pkg.frst.cloud/releases")
                name = "frstCloudReleases"

                credentials {
                    username = providers.environmentVariable("USERNAME").getOrElse("default")
                    password = providers.environmentVariable("PASSWORD").getOrElse("default")
                }
            }
        }
    }
}

java {
    withSourcesJar()
}

gradlePlugin {
    plugins {
        create("ktgen") {
            id = "gg.sona.ktgen"
            displayName = "ktgen"
            implementationClass = "net.echonolix.ktgen.KtgenPlugin"
        }
    }
}

publishing {
    repositories {
        maven {
            url = uri("https://pkg.frst.cloud/releases")
            name = "frstCloudReleases"

            credentials {
                username = providers.environmentVariable("USERNAME").getOrElse("default")
                password = providers.environmentVariable("PASSWORD").getOrElse("default")
            }
        }
    }
}

tasks {
    processResources {
        expand("version" to project.version)
    }
}