package no.skatteetaten.aurora.red.service

import io.fabric8.openshift.api.model.Route
import io.fabric8.openshift.client.DefaultOpenShiftClient
import org.springframework.stereotype.Component

@Component
class RouteService(val client: DefaultOpenShiftClient) {

    fun findRoute(namespace: String, name: String): Route? {

        return client.routes().inNamespace(namespace).withName(name).get()
    }
}