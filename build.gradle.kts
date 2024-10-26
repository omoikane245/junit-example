import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.lang.Thread.sleep

plugins {
    id("org.springframework.boot") version "2.7.7"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    id("com.avast.gradle.docker-compose") version "0.14.0"
    id("io.gitlab.arturbosch.detekt") version "1.17.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.3.0")
    implementation("com.mysql:mysql-connector-j")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.mockk:mockk:1.13.3")
    testImplementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:2.3.0")
}

detekt {
    toolVersion = "1.17.0"
    config = files("./detekt.yml")
    buildUponDefaultConfig = true
}

dockerCompose {
    useComposeFiles = listOf("./docker-compose.yml")
    isRequiredBy(project.tasks.test)
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()

    doFirst {
        sleep(10000)
    }
}
