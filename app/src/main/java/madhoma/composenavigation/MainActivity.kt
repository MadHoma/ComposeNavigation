package madhoma.composenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import madhoma.composenavigation.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {

                val navController = rememberAnimatedNavController()
                // A surface container using the 'background' color from the theme
                AnimatedNavHost(
                    navController = navController,
                    startDestination = Screens.Home.router,
                    popEnterTransition = { slideIntoContainer(AnimatedContentScope.SlideDirection.Right) },
                    popExitTransition = { slideOutOfContainer(AnimatedContentScope.SlideDirection.Right) },
                    enterTransition = { slideIntoContainer(AnimatedContentScope.SlideDirection.Left) },
                    exitTransition = { slideOutOfContainer(AnimatedContentScope.SlideDirection.Left) },
                ) {
                    addComposableScreens(navController)
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppTheme {
        Greeting("Android")
    }
}