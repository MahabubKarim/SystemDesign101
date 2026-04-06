package com.mmk.systemdesign

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.mmk.systemdesign.ui.screens.MainScreen
import com.mmk.systemdesign.ui.theme.SystemDesign101Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SystemDesign101Theme {
                MainScreen()
            }
        }
    }
}
