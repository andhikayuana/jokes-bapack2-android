package id.yuana.jokesbapack2.screen.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.yuana.jokesbapack2.data.repository.JokesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: JokesRepository
) : ViewModel() {

    companion object {
        const val IDLE = 0
        const val GOTO_JOKE = 1
    }

    val next: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>(IDLE)
    }

    init {
        viewModelScope.launch {
            delay(1000)
            repository.getAll().collect {
                next.value = GOTO_JOKE
            }


        }
    }
}