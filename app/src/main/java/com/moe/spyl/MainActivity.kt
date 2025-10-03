package com.moe.spyl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.moe.spyl.presentation.flow.core.components.SpylSharedViewModel
import com.moe.spyl.presentation.flow.core.navigation.AppNavGraph
import com.moe.spyl.presentation.flow.core.utils.rememberBaseNavController
import com.moe.spyl.ui.theme.SpylTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            val sharedViewModel = hiltViewModel<SpylSharedViewModel>()

            SpylTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppRoot(
                        widthClass = windowSizeClass.widthSizeClass,
                        modifier = Modifier.padding(innerPadding),
                        sharedViewModel = sharedViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun AppRoot(
    widthClass: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
    sharedViewModel: SpylSharedViewModel = hiltViewModel()
) {
    // use widthClass to adapt UI (rail vs labels, columns, etc.)
    val navController = rememberBaseNavController()
    AppNavGraph(
        modifier = modifier,
        navController = navController,
        sharedviewModle = sharedViewModel
    )

}


@Preview(showBackground = true)
@Composable
fun AppRootPreview() {
    SpylTheme {
        AppRoot(widthClass = WindowWidthSizeClass.Compact)

    }
}