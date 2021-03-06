plugins {
    id("org.springframework.cloud.contract")
    id("org.jetbrains.kotlin.jvm") version "1.3.41"
    id("org.jetbrains.kotlin.plugin.spring") version "1.3.41"
    id("org.jlleitschuh.gradle.ktlint") version "8.2.0"
    id("org.sonarqube") version "2.7.1"

    id("org.springframework.boot") version "2.1.7.RELEASE"
    id("org.asciidoctor.convert") version "2.3.0"

    id("com.gorylenko.gradle-git-properties") version "2.0.0"
    id("com.github.ben-manes.versions") version "0.22.0"
    id("se.patrikerdes.use-latest-versions") version "0.2.12"

    id("no.skatteetaten.gradle.aurora") version "2.4.2"
}

dependencies {
    implementation("io.fabric8:openshift-client:4.4.1")
    testImplementation("com.fkorotkov:kubernetes-dsl:2.1")

    implementation("org.springframework.boot:spring-boot-starter-hateoas")
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    testImplementation("io.mockk:mockk:1.9.3")
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.19")
    testImplementation("com.nhaarman:mockito-kotlin:1.6.0")
    testImplementation("no.skatteetaten.aurora:mockmvc-extensions-kotlin:1.0.0")
}
