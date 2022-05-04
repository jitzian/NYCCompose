package com.example.nyccompose.dagger.components

import com.example.nyccompose.dagger.modules.NetworkModule
import dagger.Component

@Component(
    modules = [
        NetworkModule::class
    ]
)
interface ComponentInjector {



    @Component.Builder
    interface Builder {
        fun networkModule(networkModule: NetworkModule): Builder
        fun build(): ComponentInjector
    }

}