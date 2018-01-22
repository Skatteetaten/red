package no.skatteetaten.aurora.red.service

import no.skatteetaten.aurora.red.service.openshift.OpenShiftResourceClient
import org.springframework.stereotype.Component

data class Route(val namespace: String, val name: String)

@Component
class RouteService(val openShiftResourceClient: OpenShiftResourceClient) {

    fun findRoute(namespace: String, name: String): Route? {

        val responseEntity = openShiftResourceClient.get("/namespaces/${namespace}/routes/${name}")
        return responseEntity?.let { Route(namespace, name) }
    }
}