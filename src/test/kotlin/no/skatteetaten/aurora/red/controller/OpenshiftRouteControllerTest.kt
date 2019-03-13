package no.skatteetaten.aurora.red.controller

import com.fkorotkov.openshift.metadata
import com.fkorotkov.openshift.newRoute
import com.nhaarman.mockito_kotlin.given
import no.skatteetaten.aurora.red.service.RouteService
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.HttpStatus.OK

@WebMvcTest(
    OpenShiftRouteController::class
)
@AutoConfigureWebClient
@AutoConfigureRestDocs
class OpenshiftRouteControllerTest : AbstractControllerTest() {

    @MockBean
    lateinit var routeService: RouteService

    val routeNamespace = "aurora"
    val routeName = "red"

    val route = newRoute {
        metadata {
            name = routeName
            namespace = routeNamespace
        }
    }

    @Test
    fun `should get response for existing route`() {

        given(routeService.findRoute(routeNamespace, routeName)).willReturn(route)
        mockMvc.get(
            docsIdentifier = "route-exists-get",
            urlTemplate = UrlTemplate("/api/route/{namespace}/{name}", routeNamespace, routeName)
        ) {
            it.status(OK)
                .responseJsonPath("$.route.namespace").equalsValue(routeNamespace)
                .responseJsonPath("$.route.name").equalsValue(routeName)
        }
    }

    @Test
    fun `should fail for nonexisting route`() {
        given(routeService.findRoute(routeNamespace, routeName)).willReturn(null)
        mockMvc.get(
            docsIdentifier = "route-notexists-get",
            urlTemplate = UrlTemplate("/api/route/{namespace}/{name}", routeNamespace, routeName)
        ) {
            it.status(NOT_FOUND)
                .responseJsonPath("$.errorMessage").equalsValue("No route named $routeName in namespace $routeNamespace")
        }
    }
}
