package madhoma.composenavigation.screens.home

sealed class NavigationItem(val route: String, val title: String) {
    object Tab1 : NavigationItem("tab1",  "Tab 1")
    object Tab2 : NavigationItem("tab2", "Tab 2")
    object Tab3 : NavigationItem("tab3", "Tab 3")
}
