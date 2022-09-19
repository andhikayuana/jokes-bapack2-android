package id.yuana.jokesbapack2.screen.joke

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import id.yuana.jokesbapack2.data.local.entity.JokeEntity
import id.yuana.jokesbapack2.data.repository.JokesRepository
import id.yuana.jokesbapack2.util.CoroutinesTestRule
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verifyOrder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class JokeViewModelTest {

    @get:Rule
    val testCoroutineRule = CoroutinesTestRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: JokeViewModel

    @MockK
    private lateinit var repository: JokesRepository

    @MockK(relaxed = true)
    private lateinit var jokeContentObserver: Observer<String>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        viewModel = JokeViewModel(repository)
//        viewModel.jokeContent.observeForever(jokeContentObserver)
    }

    @After
    fun tearDown() {
//        viewModel.jokeContent.removeObserver(jokeContentObserver)
    }

    @Test
    fun `when joke screen open up then should display random joke`() {
        testCoroutineRule.runBlockingTest {
            //given
            val dummyJoke = JokeEntity(1, "Random")
            every { repository.getRandom() } returns flowOf(dummyJoke)

            //when
            viewModel.getRandomJoke()


            //then
            verifyOrder {
                jokeContentObserver.onChanged("Loading...")
                jokeContentObserver.onChanged(dummyJoke.content)
            }

        }
    }
}