package id.yuana.jokesbapack2.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import id.yuana.jokesbapack2.screen.joke.JokeScreen
import id.yuana.jokesbapack2.screen.splash.SplashViewModel
import id.yuana.jokesbapack2.screen.splash.SplashViewModel.Companion.GOTO_JOKE
import id.yuana.jokesbapack2.ui.theme.JokesBapack2Theme

@AndroidEntryPoint
class JokesBapack2Activity : ComponentActivity() {

    private val splashViewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)

        splashViewModel.next.observe(this) { next ->
            splashScreen.setKeepOnScreenCondition { (next == GOTO_JOKE).not() }
        }

        setContent {
            JokesBapack2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    JokeScreen()
                }
            }
        }
    }
}