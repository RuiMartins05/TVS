plugins {
    id("java")
}

group = "pt.ulisboa.tecnico.STV"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.testng:testng:7.10.2")

    // https://mvnrepository.com/artifact/org.slf4j/slf4j-reload4j
    testImplementation("org.slf4j:slf4j-reload4j:2.0.13")
}

tasks.test {
    useTestNG()
}