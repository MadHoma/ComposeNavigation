package madhoma.composenavigation.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import madhoma.composenavigation.screens.home.NavigationItem

private val items = listOf<NavigationItem>(
    NavigationItem.Tab1,
    NavigationItem.Tab2,
    NavigationItem.Tab3,
)

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(rootNavController: NavController, tab: String) {
    val navController = rememberAnimatedNavController()
    Scaffold(bottomBar = { BottomBar(navController) }) {
        AnimatedNavHost(
            navController, startDestination = tab,
            popEnterTransition = { slideIntoContainer(AnimatedContentScope.SlideDirection.Right) },
            popExitTransition = { slideOutOfContainer(AnimatedContentScope.SlideDirection.Right) },
            enterTransition = { slideIntoContainer(AnimatedContentScope.SlideDirection.Left) },
            exitTransition = { slideOutOfContainer(AnimatedContentScope.SlideDirection.Left) },
        ) {
            composable(
                route = NavigationItem.Tab1.route,
            ) {
                HomeTabScreen(tab = NavigationItem.Tab1.route) { rootNavController.navigate("detail") }
            }
            composable(
                route = NavigationItem.Tab2.route,
            ) {
                HomeTabScreen(tab = NavigationItem.Tab2.route) { rootNavController.navigate("detail") }
            }
            composable(
                route = NavigationItem.Tab3.route,
            ) {
                HomeTabScreen(tab = NavigationItem.Tab3.route) { rootNavController.navigate("detail") }
            }
        }
    }

}


@Composable
private fun BottomBar(navController: NavHostController) {
    BottomNavigation(
        backgroundColor = Color.DarkGray
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        val isBackHandlerEnabled =
            currentDestination?.hierarchy?.none { it.route == NavigationItem.Tab1.route } == true
        BackHandler(isBackHandlerEnabled) {
            navController.popBackStack()
            navController.navigate(NavigationItem.Tab1.route)
        }
        items.forEach { screen ->
            val selected =
                currentDestination?.hierarchy?.any { it.route == screen.route } == true

            val ripple = rememberRipple(bounded = false, color = Color.White)
            val interactionSource: MutableInteractionSource =
                remember { MutableInteractionSource() }
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(vertical = 5.dp)
                    .selectable(
                        selected = selected,
                        onClick = {
                            navController.popBackStack()
                            navController.navigate(screen.route) {
                                launchSingleTop = true
                                anim {
                                    enter = 0
                                    exit = 0
                                }
                            }
                        },
                        enabled = true,
                        role = Role.Tab,
                        indication = ripple,
                        interactionSource = interactionSource
                    )
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                val color =
                    if (selected) Color.White else Color.White.copy(alpha = 0.5f)
                Text(
                    screen.title,
                    modifier = Modifier.align(Alignment.BottomCenter),
                    maxLines = 1,
                    fontSize = 10.sp,
                    color = color
                )
            }
        }
    }
}