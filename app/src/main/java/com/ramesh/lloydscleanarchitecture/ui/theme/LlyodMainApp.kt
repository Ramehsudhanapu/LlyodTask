package com.ramesh.lloydscleanarchitecture.ui.theme
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramesh.lloydscleanarchitecture.utility.LlyodNaviType

@Composable
fun LlyodMainApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
) {
    val navigationType: LlyodNaviType = when (windowSize) {
            WindowWidthSizeClass.Compact -> {
                LlyodNaviType.BOTTOM_NAVIGATION
            }

            else -> {
                LlyodNaviType.BOTTOM_NAVIGATION
            }
        }

        LloydMainScreen(navigationType = navigationType, modifier = modifier)
    }

