import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    maven
    java
    kotlin("jvm") version "1.3.71"
}

group = "kafka.connect.custom"
version = "1.0"

repositories {
    mavenCentral()
    // Needed for Confluent
    maven(url = "https://packages.confluent.io/maven/")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.apache.kafka", "connect-json", "2.4.1")
    implementation("org.apache.kafka", "connect-api", "2.4.1")
    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "11"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "11"
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}