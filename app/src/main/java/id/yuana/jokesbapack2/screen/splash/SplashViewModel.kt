package id.yuana.jokesbapack2.screen.splash

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.yuana.jokesbapack2.data.repository.JokesRepository
import id.yuana.jokesbapack2.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: JokesRepository
) : ViewModel() {

    companion object {
        const val IDLE = 0
        const val GOTO_JOKE = 1
        const val LOADING = 2
    }

    val next: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>(IDLE)
    }

    init {
        viewModelScope.launch {
            delay(1000)

            repository.hasLocalData().collect {
                if (it > 0) {
                    next.value = GOTO_JOKE
                } else {
                    repository.getAll()
                        .onStart {
                            next.value = LOADING
                        }
                        .catch {
                            Log.d("YUANA", it.toString())
                        }
                        .collect {
                            when (it.status) {
                                Resource.Status.SUCCESS -> {
                                    next.value = GOTO_JOKE
                                }
                                Resource.Status.ERROR -> {
                                    Log.d("YUANA", it.message.toString())
                                }
                            }

                        }
                }
            }


        }
    }
}