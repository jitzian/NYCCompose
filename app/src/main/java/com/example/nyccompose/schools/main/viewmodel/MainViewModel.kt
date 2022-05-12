package com.example.nyccompose.schools.main.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nyccompose.BaseViewModel
import com.example.nyccompose.constants.GlobalConstants.Companion.baseUrl
import com.example.nyccompose.dagger.components.DaggerComponentInjector
import com.example.nyccompose.dagger.modules.NetworkModule
import com.example.nyccompose.rest.RestApi
import com.example.nyccompose.rest.model.SchoolsResult
import com.example.nyccompose.rest.model.ScoresResult
import com.example.nyccompose.utils.safeLet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

class MainViewModel : BaseViewModel() {

    init {
        TAG = MainViewModel::class.java.simpleName
    }

    private val _data = MutableStateFlow<UIState>(UIState.Empty)
    val data: StateFlow<UIState>
        get() = _data

    fun fetchData() = viewModelScope.launch {
        try {
            coroutineScope {
                if (_data.value == UIState.Empty) {
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
        object Empty : UIState()
        class Success(val listOfSchools: SchoolsResult, val listOfScores: ScoresResult) : UIState()
        class Error(message: String) : UIState()
    }

}