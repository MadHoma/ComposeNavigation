package madhoma.composenavigation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun DetailScreen(onClickBack: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(text = "Detail", color = Color.White)
        Button(onClick = onClickBack) {
            Text(text = "Back", color = Color.White)
        }
    }
}