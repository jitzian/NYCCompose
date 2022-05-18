package com.example.nyccompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import coil.annotation.ExperimentalCoilApi
import com.example.nyccompose.ui.app.NYCApp
import com.example.nyccompose.ui.navigation.NavigationConfig

@ExperimentalCoilApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NYCApp {
                NavigationConfig()
            }
        }
    }
}