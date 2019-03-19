package no.skatteetaten.aurora.red

import io.fabric8.openshift.client.DefaultOpenShiftClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Configuration {

    @Bean
    fun client(): DefaultOpenShiftClient {
        return DefaultOpenShiftClient()
    }
}