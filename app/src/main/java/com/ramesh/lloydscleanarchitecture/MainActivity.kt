package com.ramesh.lloydscleanarchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.ramesh.assessment.navigation.model.SplashScreen
import com.ramesh.core.ui.componets.particle.theme.JetShopeeTheme
import com.ramesh.lloydscleanarchitecture.ui.theme.LlyodMainApp
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetShopeeTheme {
                Surface {
                    val windowSize = calculateWindowSizeClass(this)
                    var showSplashScreen by remember { mutableStateOf(true) }
                    if (showSplashScreen) {
                        SplashScreen(onTimeout = { showSplashScreen = false })
                    } else {
                        LlyodMainApp(windowSize = windowSize.widthSizeClass)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun JetShopeePreview() {
    JetShopeeTheme {
        Surface {
            LlyodMainApp(windowSize = WindowWidthSizeClass.Compact)
        }
    }
}