import io.micronaut.gradle.docker.MicronautDockerfile
import io.micronaut.gradle.docker.NativeImageDockerfile

plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.micronaut.application") version "4.2.1"
    id("io.micronaut.test-resources") version "4.2.1"
    id("io.micronaut.aot") version "4.2.1"
}

version = "0.1"
group = "demo"

repositories {
    mavenCentral()
}

dependencies {
    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("io.micronaut.data:micronaut-data-processor")
    annotationProcessor("io.micronaut:micronaut-http-validation")
    annotationProcessor("io.micronaut.openapi:micronaut-openapi")
    annotationProcessor("io.micronaut.security:micronaut-security-annotations")
    annotationProcessor("io.micronaut.validation:micronaut-validation-processor")
    implementation("io.micrometer:context-propagation")
    implementation("io.micronaut:micronaut-jackson-databind")
    implementation("io.micronaut.data:micronaut-data-r2dbc")
    implementation("io.micronaut.flyway:micronaut-flyway")
    implementation("io.micronaut.reactor:micronaut-reactor")
    implementation("io.micronaut.reactor:micronaut-reactor-http-client")
    implementation("io.micronaut.redis:micronaut-redis-lettuce")
    implementation("io.micronaut.security:micronaut-security-jwt")
    implementation("io.micronaut.sql:micronaut-jdbc-hikari")
    implementation("io.micronaut.validation:micronaut-validation")
    implementation("jakarta.validation:jakarta.validation-api")
    implementation("at.favre.lib:bcrypt:0.10.2")
    implementation("jakarta.persistence:jakarta.persistence-api")
    compileOnly("io.micronaut:micronaut-http-client")
    compileOnly("io.micronaut.openapi:micronaut-openapi-annotations")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("org.postgresql:r2dbc-postgresql")
    runtimeOnly("org.yaml:snakeyaml")
    testImplementation("io.micronaut:micronaut-http-client")
    testImplementation("org.awaitility:awaitility:4.2.0")
    aotPlugins(platform("io.micronaut.platform:micronaut-platform:4.2.2"))
    aotPlugins("io.micronaut.security:micronaut-security-aot")
}


application {
    mainClass.set("demo.Application")
}
java {
    sourceCompatibility = JavaVersion.toVersion("21")
    targetCompatibility = JavaVersion.toVersion("21")
}


graalvmNative.toolchainDetection.set(false)
micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("demo.*")
    }
    aot {
    // Please review carefully the optimizations enabled below
    // Check https://micronaut-projects.github.io/micronaut-aot/latest/guide/ for more details
        optimizeServiceLoading.set(false)
        convertYamlToJava.set(false)
        precomputeOperations.set(true)
        cacheEnvironment.set(true)
        optimizeClassLoading.set(true)
        deduceEnvironment.set(true)
        optimizeNetty.set(true)
        configurationProperties.put("micronaut.security.jwks.enabled","false")
    }
}

tasks.named<NativeImageDockerfile>("optimizedDockerfileNative") {
    graalImage.set("ghcr.io/graalvm/native-image-community:21.0.1-ol8")
}

tasks.named<NativeImageDockerfile>("dockerfileNative") {
    graalImage.set("ghcr.io/graalvm/native-image-community:21.0.1-ol8")
}

tasks.named<MicronautDockerfile>("optimizedDockerfile") {
    baseImage.set("eclipse-temurin:21-jre-alpine")
}

tasks.named<MicronautDockerfile>("dockerfile") {
    baseImage.set("eclipse-temurin:21-jre-alpine")
}


