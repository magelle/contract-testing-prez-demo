import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.2.0.RELEASE"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	id("au.com.dius.pact") version "4.0.2"
	kotlin("jvm") version "1.3.50"
	kotlin("plugin.spring") version "1.3.50"
}

group = "contract-testing"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation("au.com.dius:pact-jvm-provider-junit5:4.0.2")
}

tasks.withType<Test> {
	useJUnitPlatform()
	systemProperty("pact.rootDir", "build/pacts")
	systemProperty("pact.provider.version", version)
	systemProperty("pact.verifier.publishResults", true)
	systemProperty("pact.broker.host", "magelle.pact.dius.com.au")
	systemProperty("pact.broker.username", "1gX52gsVt9iwwVuD9ciMaA")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
