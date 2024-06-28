package com.example.sprapplication.ui.presentation

import android.os.Bundle
import android.util.Log
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.common.ui.DetailScreen
import com.example.common.ui.ScreenDetail
import com.example.common.ui.appBarUI
import com.example.sprapplication.ui.theme.SprApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    var checkResume = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SprApplicationTheme {
//                testUI()
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "firstscreen"
                ) {
                    composable("firstscreen") {
                        Surface(modifier = Modifier.fillMaxWidth(), color = Color.Black) {
                            appBarUI(navController)
                        }
                    }
                    composable(
                        "secondscreen/{banner_id}",
                        arguments = listOf(navArgument("banner_id") { type = NavType.IntType })
                    ) { backStackEntry ->
                        ScreenDetail(backStackEntry.arguments?.getInt("banner_id"))
                    }
                    composable("detailScreen") {
                        DetailScreen()
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (checkResume == 0) {
            Log.d("check_resume", "first launch")
        } else {
            Log.d("check_resume", "restart launch")
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("check_resume", checkResume.toString())
    }

    override fun onRestart() {
        super.onRestart()
        checkResume = 1
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        SprApplicationTheme {
            Greeting("Android")
        }
    }
}