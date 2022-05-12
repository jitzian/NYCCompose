package com.example.nyccompose

import androidx.lifecycle.ViewModel
import com.example.nyccompose.constants.GlobalConstants
import com.example.nyccompose.dagger.components.DaggerComponentInjector
import com.example.nyccompose.dagger.modules.NetworkModule
import com.example.nyccompose.rest.RestApi
import retrofit2.Retrofit
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    protected lateinit var TAG: String

    private val injector = DaggerComponentInjector.builder()
        .networkModule(NetworkModule(baseUrl = GlobalConstants.baseUrl))
        .build()

    protected var restApi: RestApi

    @Inject
    lateinit var retrofit: Retrofit

    init {
        inject()
        restApi = retrofit.create(RestApi::class.java)
    }

    private fun inject() {
        injector.inject(this)
    }

}