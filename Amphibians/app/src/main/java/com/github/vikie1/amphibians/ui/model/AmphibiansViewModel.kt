package com.github.vikie1.amphibians.ui.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.github.vikie1.amphibians.AmphibiansApp
import com.github.vikie1.amphibians.data.AmphibiansRepository
import com.github.vikie1.amphibians.network.Amphibian
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface AmphibiansUiState{
    data class Success(val amphibians: List<Amphibian>): AmphibiansUiState
    data object Error : AmphibiansUiState
    data object Loading : AmphibiansUiState
}

class AmphibiansViewModel(private val amphibiansRepository: AmphibiansRepository) : ViewModel() {
    var amphibiansUiState: AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
        private set

    init {
        loadAmphibians()
    }

    fun loadAmphibians(){
        viewModelScope.launch {
            amphibiansUiState = try {
                AmphibiansUiState.Success(amphibiansRepository.loadAmphibiansApi())
            } catch (e: IOException){
                AmphibiansUiState.Error
            }
        }
    }

    companion object {
         val Factory: ViewModelProvider.Factory = viewModelFactory {
             initializer {
                 val application = (this[APPLICATION_KEY] as AmphibiansApp)
                 val amphibiansRepository = application.appContainer.amphibiansRepository
                 AmphibiansViewModel(amphibiansRepository)
             }
         }
    }
}