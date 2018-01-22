package no.skatteetaten.aurora.red.service.openshift

interface TokenProvider {
    fun getToken(): String
}