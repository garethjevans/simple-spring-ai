plugins {
    java
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "simple.spring.ai"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
    maven {
        name = "Spring Milestones"
        url = uri("https://repo.spring.io/milestone")
        mavenContent {
            includeGroupByRegex("org\\.springframework\\.ai.*")  // Allow milestone versions for Spring AI
        }
    }
    maven {
        name = "Spring Snapshots"
        url = uri("https://repo.spring.io/snapshot")
        mavenContent {
            snapshotsOnly()
        }
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.ai:spring-ai-openai-spring-boot-starter:1.0.0-M2-PLATFORM")
    //implementation("org.springframework.boot:spring-boot-starter-actuator")

    implementation("io.pivotal.cfenv:java-cfenv-boot:3.2.0")

    //testImplementation("org.springframework.boot:spring-boot-starter-test")
    //testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.register<Jar>("testJar") {
    archiveClassifier.set("tests")  // Name the JAR as "projectname-tests.jar"
    from(sourceSets["test"].output) // Include test classes and resources
}

artifacts {
    add("archives", tasks.named("testJar"))
}

tasks.named("build") {
    dependsOn("testJar")
}

