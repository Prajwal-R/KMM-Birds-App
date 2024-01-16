package dev.prajwal.shared.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import dev.prajwal.shared.model.BirdImage
import dev.prajwal.app.BirdsViewModel

class FetchBirdsDataRepository() {
    private val httpClient =
        HttpClient {
            install(ContentNegotiation) {
                json()
            }
        }

    suspend fun getImages(): List<BirdImage> =
        httpClient
            .get(BirdsViewModel.BASE_URL + "pictures.json")
            .body<List<BirdImage>>()
}
