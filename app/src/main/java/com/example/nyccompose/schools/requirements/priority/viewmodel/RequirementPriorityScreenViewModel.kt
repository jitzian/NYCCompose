package com.example.nyccompose.schools.requirements.priority.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.nyccompose.base.BaseViewModel
import com.example.nyccompose.constants.GlobalConstants.Companion.MAX_TIME_OUT
import com.example.nyccompose.schools.requirements.priority.domain.model.RequirementPriority
import com.example.nyccompose.utils.safeLet
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RequirementPriorityScreenViewModel : BaseViewModel() {

    private val _state = MutableStateFlow(UIState())
    val state = _state.asStateFlow()

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
                        _state.value = UIState(isLoading = false, data = listOfRequirementPriority)
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "fetchData::${e.message}")
            } catch (toe: TimeoutCancellationException) {
                Log.e(TAG, "fetchData: ${toe.message}")
            }
        }
    }

    private suspend fun fetchSchools() = restApi.fetchSchools()
    private suspend fun fetchScores() = restApi.fetchScores()

    data class UIState(
        var isLoading: Boolean = true,
        var isError: Boolean = false,
        var data: List<RequirementPriority> = emptyList()
    )

}