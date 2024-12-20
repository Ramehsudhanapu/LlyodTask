package com.ramesh.lloydscleanarchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.ramesh.assessment.splash.SplashScreen
import com.ramesh.core.ui.componets.particle.theme.LlyodTheme
import com.ramesh.lloydscleanarchitecture.ui.theme.LlyodMainApp
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LlyodTheme {
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
    LlyodTheme {
        Surface {
            LlyodMainApp(windowSize = WindowWidthSizeClass.Compact)
        }
    }
}