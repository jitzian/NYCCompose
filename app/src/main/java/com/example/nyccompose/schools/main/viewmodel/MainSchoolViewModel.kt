package com.example.nyccompose.schools.main.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.nyccompose.base.BaseViewModel
import com.example.nyccompose.rest.model.SchoolsResult
import com.example.nyccompose.rest.model.ScoresResult
import com.example.nyccompose.utils.safeLet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainSchoolViewModel : BaseViewModel() {

    init {
        TAG = MainSchoolViewModel::class.java.simpleName
    }

    private val _data = MutableStateFlow<UIState>(UIState.Loading)
    val data: StateFlow<UIState>
        get() = _data.asStateFlow()

    fun fetchData() = viewModelScope.launch {
        try {
            coroutineScope {
                if (_data.value == UIState.Loading) {
                    val schools = async(Dispatchers.IO) {
                        fetchSchools()
                    }

                    val scores = async(Dispatchers.IO) {
                        fetchScores()
                    }

                    val combinedResult =
                        safeLet(schools.await(), scores.await()) { safeSchools, safeScores ->
                            _data.value = UIState.Success(
                                safeSchools, safeScores
                            )
                        }

                    if (combinedResult == null) {
                        _data.value = UIState.Error("Something went wrong")
                    }
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "fetchData: ${e.message}")
            _data.value = UIState.Error(e.message ?: "Error Occurred")
        }
    }

    private suspend fun fetchSchools() = restApi.fetchSchools()

    private suspend fun fetchScores() = restApi.fetchScores()

    sealed class UIState {
        object Loading : UIState()
        class Success(val listOfSchools: SchoolsResult, val listOfScores: ScoresResult) : UIState()
        class Error(message: String) : UIState()
    }

}