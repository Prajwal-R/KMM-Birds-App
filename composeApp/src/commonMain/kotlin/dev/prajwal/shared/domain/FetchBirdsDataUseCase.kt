package dev.prajwal.shared.domain

import dev.prajwal.shared.service.FetchBirdsDataRepository

class FetchBirdsDataUseCase(
    private val fetchBirdsDataRepository: FetchBirdsDataRepository,
) {
    suspend operator fun invoke() = fetchBirdsDataRepository.getImages()
}
