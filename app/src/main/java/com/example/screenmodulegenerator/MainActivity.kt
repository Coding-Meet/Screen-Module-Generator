package com.example.screenmodulegenerator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.screenmodulegenerator.product.presentation.ProductListScreen
import com.example.screenmodulegenerator.ui.theme.ScreenModuleGeneratorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScreenModuleGeneratorTheme {
                ProductListScreen()
            }
        }
    }
}

