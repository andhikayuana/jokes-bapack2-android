package id.yuana.jokesbapack2.screen.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import id.yuana.jokesbapack2.data.local.entity.JokeEntity
import id.yuana.jokesbapack2.data.repository.JokesRepository
import id.yuana.jokesbapack2.util.CoroutinesTestRule
import id.yuana.jokesbapack2.util.Resource
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
class SplashViewModelTest {

    @get:Rule
    val testCoroutineRule = CoroutinesTestRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: SplashViewModel

    @MockK
    private lateinit var repository: JokesRepository

    @MockK(relaxed = true)
    private lateinit var nextObserver: Observer<Int>

    //todo
    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        viewModel = SplashViewModel(repository)
        viewModel.next.observeForever(nextObserver)
    }

    @After
    fun tearDown() {
        viewModel.next.removeObserver(nextObserver)
    }

    @Test
    fun `when open splash screen and local data empty then should goto joke screen`() {
        testCoroutineRule.runBlockingTest {
            //given
            every { repository.hasLocalData() } returns flowOf(0)
            every { repository.getAll() } returns flowOf(
                Resource.success(
                    listOf(
                        JokeEntity(
                            1,
                            "Random"
                        )
                    )
                )
            )

            //when
//            advanceTimeBy(1000)

            //then
            verifyOrder {
                nextObserver.onChanged(SplashViewModel.IDLE)
                nextObserver.onChanged(SplashViewModel.LOADING)
                nextObserver.onChanged(SplashViewModel.GOTO_JOKE)
            }

        }
    }

    @Test
    fun `when open splash screen and has local data then should goto joke screen`() {
        testCoroutineRule.runBlockingTest {
            //given
            every { repository.hasLocalData() } returns flowOf(2)
            every { repository.getAll() } returns flowOf(
                Resource.success(
                    listOf(
                        JokeEntity(
                            1,
                            "Random"
                        ),
                        JokeEntity(
                            2,
                            "Random2"
                        )
                    )
                )
            )

            //when
//            advanceTimeBy(1000)

            //then
            verifyOrder {
                nextObserver.onChanged(SplashViewModel.IDLE)
                nextObserver.onChanged(SplashViewModel.GOTO_JOKE)
            }

        }
    }

}