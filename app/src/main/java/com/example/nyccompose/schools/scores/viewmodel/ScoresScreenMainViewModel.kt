package com.example.nyccompose.schools.scores.viewmodel

import com.example.nyccompose.BaseViewModel
import com.example.nyccompose.rest.model.ScoresResultItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ScoresScreenMainViewModel : BaseViewModel() {

    private val _state = MutableStateFlow<UIState>(UIState.Empty)
    val state: StateFlow<UIState>
        get() = _state.asStateFlow()

    init {
        TAG = ScoresScreenMainViewModel::class.java.simpleName
    }

    sealed class UIState {
        object Empty : UIState()
        class Success(val listOfScores: List<ScoresResultItem>) : UIState()
        class Error(val message: String) : UIState()
    }

}