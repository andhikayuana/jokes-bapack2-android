package id.yuana.jokesbapack2.screen.joke

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.yuana.jokesbapack2.data.repository.JokesRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokeViewModel @Inject constructor(
    private val jokesRepository: JokesRepository
) : ViewModel() {

    //    private val _jokeContent: MutableLiveData<String> by lazy {
//        MutableLiveData("Loading...")
//    }
//    val jokeContent: LiveData<String>
//        get() = _jokeContent
    private val _jokeContent = mutableStateOf("Loading...")
    val jokeContent: State<String>
        get() = _jokeContent

    init {
        getRandomJoke()
    }

    fun getRandomJoke() {
        viewModelScope.launch {
            jokesRepository.getRandom().filterNotNull().collect {
                _jokeContent.value = it.content
            }

        }
    }
}