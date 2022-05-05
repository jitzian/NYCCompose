package com.example.nyccompose.schools.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nyccompose.constants.GlobalConstants.Companion.baseUrl
import com.example.nyccompose.dagger.components.DaggerComponentInjector
import com.example.nyccompose.dagger.modules.NetworkModule
import com.example.nyccompose.rest.RestApi
import com.example.nyccompose.rest.model.SchoolsResult
import com.example.nyccompose.rest.model.ScoresResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

class MainViewModel : ViewModel() {
    private val TAG = MainViewModel::class.simpleName

    private val injector = DaggerComponentInjector.builder()
        .networkModule(NetworkModule(baseUrl))
        .build()

    private var restApi: RestApi

    @Inject
    lateinit var retrofit: Retrofit

    init {
        inject()
        restApi = retrofit.create(RestApi::class.java)
    }

    private fun inject() {
        injector.inject(this)
    }

    private val _data = MutableStateFlow<UIState>(UIState.Empty)
    val data: StateFlow<UIState>
        get() = _data

    fun fetchData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {

        }
    }

    sealed class UIState {
        object Empty : UIState()
        class Success(val listOfSchools: SchoolsResult, val listOfScores: ScoresResult) : UIState()
        class Error(message: String)
    }

}