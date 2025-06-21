package org.kvxd.csapi.client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json

class CSAPIClient(
    private val baseEndpoint: String = "csapi.kvxd.org",
    private val port: Int = 8080
) {

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }

        install(HttpRequestRetry) {
            retryOnServerErrors(maxRetries = 3)
            exponentialDelay()
        }
    }

    suspend fun items(): ApiResponse<Set<Item>> =
        client.get {
            url {
                protocol = URLProtocol.HTTP
                host = baseEndpoint
                port = this@CSAPIClient.port
                path("api", "v1", "items")
            }
        }.body()

    suspend fun itemsByWeapon(weaponName: String): ApiResponse<Item?> =
        client.get {
            url {
                protocol = URLProtocol.HTTP
                host = baseEndpoint
                port = this@CSAPIClient.port
                path("api", "v1", "items", weaponName)
            }
        }.body()

    suspend fun skin(weaponName: String, skinName: String): ApiResponse<Skin?> =
        client.get {
            url {
                protocol = URLProtocol.HTTP
                host = baseEndpoint
                port = this@CSAPIClient.port
                path("api", "v1", "items", weaponName, skinName)
            }
        }.body()

    suspend fun skinsByFloatRange(weaponName: String, minFloat: Float = 0.0f, maxFloat: Float = 1.0f): ApiResponse<List<Skin>> =
        client.get {
            url {
                protocol = URLProtocol.HTTP
                host = baseEndpoint
                port = this@CSAPIClient.port
                path("api", "v1", "items", weaponName, "float-range")
                parameter("min", minFloat)
                parameter("max", maxFloat)
            }
        }.body()

    suspend fun suggest(query: String): ApiResponse<List<SkinSuggestion>> =
        client.get {
            url {
                protocol = URLProtocol.HTTP
                host = baseEndpoint
                port = this@CSAPIClient.port
                path("api", "v1", "items", "suggest", query)
            }
        }.body()

    suspend fun collections(): ApiResponse<List<String>> =
        client.get {
            url {
                protocol = URLProtocol.HTTP
                host = baseEndpoint
                port = this@CSAPIClient.port
                path("api", "v1", "collections")
            }
        }.body()

    suspend fun itemsByCollection(collectionName: String): ApiResponse<List<Item>> =
        client.get {
            url {
                protocol = URLProtocol.HTTP
                host = baseEndpoint
                port = this@CSAPIClient.port
                path("api", "v1", "collections", collectionName)
            }
        }.body()

    suspend fun marketHashName(marketHashName: String): ApiResponse<MarketHashResponse> =
        client.get {
            url {
                protocol = URLProtocol.HTTP
                host = baseEndpoint
                port = this@CSAPIClient.port
                path("api", "v1", "market-hash-name", marketHashName)
            }
        }.body()

}