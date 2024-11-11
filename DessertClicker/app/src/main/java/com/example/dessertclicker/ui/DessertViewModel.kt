package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DessertUiState())
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()

    private val desserts: List<Dessert> = Datasource.dessertList

    fun updateRevenue(currentDessertPrice: Int){
       _uiState.update { currentUiState ->
           currentUiState.copy(
               revenue = currentUiState.revenue.plus(currentDessertPrice),
               dessertsSold = currentUiState.dessertsSold.inc()
           )
       }
    }


    /**
     * Determine which dessert to show.
     */
    fun updateDessert(dessertsSold: Int){
        var dessertToShow = desserts.first()
        for (dessert in desserts) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }

        _uiState.update { currentUiState -> currentUiState.copy(currentDessert = dessertToShow) }
    }
}