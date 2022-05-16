package com.example.nyccompose.schools.scores.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.nyccompose.BaseViewModel
import com.example.nyccompose.constants.GlobalConstants
import com.example.nyccompose.rest.model.ScoresResultItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

class ScoresScreenViewModel : BaseViewModel() {

    private val _state = MutableStateFlow<UIState>(UIState.Loading)
    val state: StateFlow<UIState>
        get() = _state.asStateFlow()

    init {
        TAG = ScoresScreenViewModel::class.java.simpleName
    }

    fun fetchScores() = viewModelScope.launch(Dispatchers.IO) {
        withTimeout(GlobalConstants.MAX_TIME_OUT) {
            try {
                val listOfScores = restApi.fetchScores()
                if (listOfScores.isNotEmpty()) {
                    _state.value = UIState.Success(listOfScores)
                } else {
                    _state.value = UIState.Error(message = "No data available")
                }
            } catch (e: Exception) {
                Log.e(TAG, "fetchScores::${e.message}")

                _state.value = UIState.Error(message = e.message ?: run {
                    "An error occurred while fetching the data"
                })
            }
        }
    }

    sealed class UIState {
        object Loading : UIState()
        class Success(val listOfScores: List<ScoresResultItem>) : UIState()
        class Error(val message: String) : UIState()
    }

}