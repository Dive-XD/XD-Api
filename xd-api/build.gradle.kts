import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    implementation(project(":xd-infra"))
    implementation(project(":xd-lib:jwt"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
}

tasks.withType<BootJar> {
    enabled = true
}