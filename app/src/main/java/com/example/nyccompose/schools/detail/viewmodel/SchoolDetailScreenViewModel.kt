package com.example.nyccompose.schools.detail.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.nyccompose.BaseViewModel
import com.example.nyccompose.constants.GlobalConstants
import com.example.nyccompose.rest.model.SchoolsResultItem
import com.example.nyccompose.ui.navigation.NavArg
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

class SchoolDetailViewModel(savedStateHandle: SavedStateHandle) : BaseViewModel() {

    private val dbn = savedStateHandle.get<String>(NavArg.Dbn.key) ?: 0

    private val _state = MutableStateFlow<UIState>(UIState.Loading)
    val state: StateFlow<UIState>
        get() = _state.asStateFlow()

    init {
        TAG = SchoolDetailViewModel::class.java.simpleName
        viewModelScope.launch(Dispatchers.IO) {
            withTimeout(GlobalConstants.MAX_TIME_OUT) {
                try {
                    val listOfSchools = restApi.fetchSchools().filter {
                        it.dbn == dbn
                    }
                    if (listOfSchools.isNotEmpty()) {
                        _state.value = UIState.Success(school = listOfSchools.first())
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "init:${e.message}")
                    _state.value = UIState.Error(
                        message = e.message ?: "An error occurred while fetching the data"
                    )
                }
            }
        }
    }

    sealed class UIState {
        object Loading : UIState()
        class Success(val school: SchoolsResultItem) : UIState()
        class Error(val message: String) : UIState()
    }


}