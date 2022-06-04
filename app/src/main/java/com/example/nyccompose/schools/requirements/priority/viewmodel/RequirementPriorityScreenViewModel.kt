package com.example.nyccompose.schools.requirements.priority.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.nyccompose.base.BaseViewModel
import com.example.nyccompose.constants.GlobalConstants.Companion.MAX_TIME_OUT
import com.example.nyccompose.schools.requirements.priority.domain.model.RequirementPriority
import com.example.nyccompose.utils.safeLet
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RequirementPriorityScreenViewModel : BaseViewModel() {

    private val _state = MutableStateFlow<UIState>(UIState.Loading)
    val state: StateFlow<UIState>
        get() = _state.asStateFlow()

    init {
        TAG = RequirementPriorityScreenViewModel::class.java.simpleName
        fetchData()
    }

    private fun fetchData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            try {
                withTimeout(MAX_TIME_OUT) {
                    val schools = fetchSchools()
                    val scores = fetchScores()

                    safeLet(schools, scores) { safeSchools, safeScores ->

                        val listOfRequirementPriority = mutableListOf<RequirementPriority>()
                        /*val filteredList =
                            safeSchools.intersect(safeScores) { schoolItem, scoreItem ->
                                schoolItem.dbn == scoreItem.dbn
                            }*/
                        for (schoolItem in safeSchools) {
                            for (scoreItem in safeScores) {
                                if (schoolItem.dbn == scoreItem.dbn) {
                                    listOfRequirementPriority.add(
                                        RequirementPriority(
                                            dbn = schoolItem.dbn,
                                            schoolName = schoolItem.schoolName,
                                            overViewParagraph = schoolItem.overviewParagraph,
                                            numOfSatTestTakers = scoreItem.numOfSatTestTakers,
                                            satCriticalReadingAvgScore = scoreItem.readingScore,
                                            satMathAvgScore = scoreItem.mathScore,
                                            satWritingAvgScore = scoreItem.writingScore
                                        )
                                    )
                                }
                            }
                        }
                        if (listOfRequirementPriority.isNotEmpty()) {
                            _state.value = UIState.Success(data = listOfRequirementPriority)
                        } else {
                            _state.value = UIState.Error(message = "No data available")
                        }
                    }
                }
            } catch (e: Exception) {
                _state.value = UIState.Error(message = "${e.message}")
            } catch (toe: TimeoutCancellationException) {
                _state.value = UIState.Error(message = "${toe.message}")
            }
        }
    }

    private suspend fun fetchSchools() = restApi.fetchSchools()
    private suspend fun fetchScores() = restApi.fetchScores()

    sealed class UIState {
        object Loading : UIState()
        class Success(val data: List<RequirementPriority> = emptyList()) : UIState()
        class Error(val message: String) : UIState()
    }

}