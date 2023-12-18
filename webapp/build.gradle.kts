plugins {
	java
	id("org.springframework.boot")
	id("io.spring.dependency-management")
}

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

dependencies {
	implementation(project(":dataprovider-core"))
	implementation(project(":coingecko-dataprovider"))

	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-validation")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
}
