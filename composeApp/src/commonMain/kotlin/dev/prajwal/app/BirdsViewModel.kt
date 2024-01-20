package dev.prajwal.app

import androidx.compose.runtime.Stable
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.prajwal.shared.domain.FetchBirdsDataUseCase
import dev.prajwal.shared.model.BirdImage
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BirdsViewModel(
    private val fetchBirdsDataUseCase: FetchBirdsDataUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(BirdsUiState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    private val intentFlow = MutableSharedFlow<BirdsScreenIntent>()

    init {
        updateImages()
        initHandleIntents()
    }

    fun dispatch(intent: BirdsScreenIntent) {
        viewModelScope.launch {
            intentFlow.emit(intent)
        }
    }

    private fun initHandleIntents() {
        viewModelScope.launch {
            intentFlow
                .collect {
                    handleIntent(it)
                }
        }
    }

    private fun handleIntent(intent: BirdsScreenIntent) {
        when(intent) {
            is BirdsScreenIntent.OnCategorySelected -> {
                selectCategory(intent.category)
            }
            is BirdsScreenIntent.OnBirdSelected -> {
                onSelectImage(intent.bird)
            }
        }
    }

    private fun selectCategory(category: String) {
        _uiState.update {
            it.copy(selectedCategory = category)
        }
    }

    private fun updateImages() {
        viewModelScope.launch {
            try {
                val images = fetchBirdsDataUseCase()
                _uiState.update {
                    it.copy(images = images, isLoading = false)
                }
            } catch (ex: Exception) {
                println(ex)
            }
        }
    }

    private fun onSelectImage(selectedBird: BirdImage?) {
        _uiState.update {
            it.copy(selectedImage = selectedBird)
        }
    }

    companion object {
        const val BASE_URL = "https://sebi.io/demo-image-api/" //"http://localhost:800/" //
    }
}

sealed class BirdsScreenIntent {
    data class OnCategorySelected(val category: String): BirdsScreenIntent()
    data class OnBirdSelected(val bird: BirdImage?): BirdsScreenIntent()
}

@Stable
data class BirdsUiState(
    val isLoading: Boolean = false,
    val images: List<BirdImage> = emptyList(),
    val selectedCategory: String? = null,
    val selectedImage: BirdImage? = null,
) {
    val categories = images.map { it.category }.toSet()
    val selectedCategoryImages = images.filter { it.category == selectedCategory }
}
