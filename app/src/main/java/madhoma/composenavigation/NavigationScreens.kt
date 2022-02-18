package madhoma.composenavigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import madhoma.composenavigation.screens.DetailScreen
import madhoma.composenavigation.screens.HomeScreen

sealed class Screens(val router: String) {
    object Home : Screens("home/{tab}")
    object Detail : Screens("detail")
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addComposableScreens(navController: NavController) {

    composable(
        "home/{tab}",
    ) { backStackEntry ->
        HomeScreen(
            rootNavController = navController,
            backStackEntry . arguments ?. getString ("tab") ?: "tab1"
        )
    }

    composable(
        Screens.Detail.router
    ) {
        DetailScreen() { navController.popBackStack() }
    }
}