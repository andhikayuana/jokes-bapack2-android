package id.yuana.jokesbapack2.screen.joke

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.yuana.jokesbapack2.data.repository.JokesRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokeViewModel @Inject constructor(
    private val jokesRepository: JokesRepository
) : ViewModel() {


    val jokeContent: MutableLiveData<String> by lazy {
        MutableLiveData("Loading...")
    }

    init {
        getRandomJoke()
    }

    fun getRandomJoke() {
        viewModelScope.launch {
            jokesRepository.getRandom().collect {
                jokeContent.value = it
            }

        }
    }
}