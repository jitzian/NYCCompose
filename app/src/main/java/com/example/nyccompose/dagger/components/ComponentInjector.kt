package com.example.nyccompose.dagger.components

import com.example.nyccompose.dagger.modules.NetworkModule
import com.example.nyccompose.schools.main.viewmodel.MainViewModel
import dagger.Component

@Component(
    modules = [
        NetworkModule::class
    ]
)
interface ComponentInjector {

    fun inject(mainViewModel: MainViewModel)

    @Component.Builder
    interface Builder {
        fun networkModule(networkModule: NetworkModule): Builder
        fun build(): ComponentInjector
    }

}