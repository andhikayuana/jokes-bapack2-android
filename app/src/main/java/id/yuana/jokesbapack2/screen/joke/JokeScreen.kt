package id.yuana.jokesbapack2.screen.joke

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import id.yuana.jokesbapack2.ui.theme.JokesBapack2Theme
import id.yuana.jokesbapack2.ui.theme.Typography
import id.yuana.jokesbapack2.ui.theme.generateColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JokeScreen(
    viewModel: JokeViewModel = viewModel()
) = viewModel.run {
    Scaffold() { contentPadding ->
        Box(
            modifier = Modifier
                .padding(contentPadding)
                .background(generateColor())
                .fillMaxSize()
                .clickable {
                    getRandomJoke()
                },
            contentAlignment = Alignment.Center,
        ) {

            Text(
                text = jokeContent.value,
                style = Typography.headlineLarge,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun DefaultPreview() {
    JokesBapack2Theme {
        JokeScreen()
    }
}