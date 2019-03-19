package no.skatteetaten.aurora.red.controller

import no.skatteetaten.aurora.red.service.RouteService
import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

class RouteResource(val route: RedRoute) : ResourceSupport() {
    init {
        add(
            linkTo(
                methodOn(OpenShiftRouteController::class.java).route(
                    route.namespace,
                    route.name
                )
            ).withSelfRel()
        )
    }
}

data class RedRoute(val namespace: String, val name: String)

@RestController
@RequestMapping(value = ["/api/route"], produces = arrayOf("application/hal+json"))
class OpenShiftRouteController(val routeService: RouteService) {

    @GetMapping("/{namespace}/{name}")
    fun route(@PathVariable namespace: String, @PathVariable name: String): HttpEntity<RouteResource> {

        val route = routeService.findRoute(namespace, name)
            ?: throw NoSuchResourceException("No route named $name in namespace $namespace")

        return ResponseEntity(RouteResource(RedRoute(route.metadata.namespace, route.metadata.name)), HttpStatus.OK)
    }
}