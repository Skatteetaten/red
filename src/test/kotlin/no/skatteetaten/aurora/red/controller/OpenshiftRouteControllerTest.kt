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
    /*
       def "Response for nonexisting route"() {

    routeService.findRoute(_, _) >> null
    when:
      ResultActions result = mockMvc.
          perform(RestDocumentationRequestBuilders.get('/api/route/{routeNamespace}/{routeName}', NAMESPACE, ROUTE))

    then:
      result
          .andExpect(MockMvcResultMatchers.status().isNotFound())
          .andDo(
          prettyDoc('route-notexists-get',
              relaxedResponseFields(
                  fieldWithPath("errorMessage").type(STRING).description("The error message describing what went wrong")
              )))
  }
    @Test
    fun `should get error if incorrect routeNamespace in token`() {
        mockMvc.get(
            headers = HttpHeaders().authorization("Bearer <token>"),
            docsIdentifier = "error-pods",
            urlTemplate = UrlTemplate("/api/pods/{routeNamespace}", "sith")
        ) {
            it.status(UNAUTHORIZED)
                .responseJsonPath("$.success").equalsValue(false)
                .responseJsonPath("$.message").equalsValue("Only an application in the same routeNamespace can use clerk.")
        }
    }


def "Response for existing route"() {

    routeService.findRoute(_, _) >> new Route(NAMESPACE, ROUTE)
    when:
      ResultActions result = mockMvc.
          perform(RestDocumentationRequestBuilders.get('/api/route/{routeNamespace}/{routeName}', NAMESPACE, ROUTE))

    then:
      result
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andDo(prettyDoc('route-exists-get',
          pathParameters(
              parameterWithName("routeNamespace").description("The routeNamespace of the route"),
              parameterWithName("routeName").description("The routeName of the route within the specified routeNamespace")
          ),
          relaxedResponseFields(
              fieldWithPath("route.routeNamespace").type(STRING).description("The routeNamespace of the requested route"),
              fieldWithPath("route.routeName").type(STRING).description("The routeName of the requested route")
          )
      ))
  }
*/
}
