package id.yuana.jokesbapack2.data.remote

import id.yuana.jokesbapack2.di.RemoteModule
import id.yuana.jokesbapack2.util.MockResponseFileReader
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

@RunWith(MockitoJUnitRunner::class)
class JokesBapack2ApiTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var api: JokesBapack2Api

    @Before
    fun setUp() {

        mockWebServer = MockWebServer()
        mockWebServer.start(9090)

        val okHttpClient = RemoteModule.provideOkHttpClient()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://localhost:9090")
            .client(okHttpClient)
            .build()
        api = RemoteModule.provideJokesBapack2Api(retrofit)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    private fun createMockResponse(
        fileName: String,
        responseCode: Int = HttpURLConnection.HTTP_OK
    ) = MockResponse()
        .setResponseCode(responseCode)
        .setBody(MockResponseFileReader(fileName).content)


    @Test
    fun `when request getAll should return success`() {

        //given
        mockWebServer.enqueue(createMockResponse("response_get_all_success.json"))

        //when
        val actualResponse = runBlocking {
            api.getAll()
        }

        //then
        assertNotNull(actualResponse)
        assertEquals(true, actualResponse.isSuccess)
        actualResponse.getOrNull()?.let { actual ->

            assertEquals("Success", actual.msg)
            assertEquals(200, actual.code)
            assertNotNull(actual.data)
            assertEquals(4, actual.data.size)
        }
    }

    @Test
    fun `when request getRandom should return success`() {
        //given
        mockWebServer.enqueue(createMockResponse("response_get_random_success.json"))

        //when
        val actualResponse = runBlocking {
            api.getRandom()
        }

        //then
        assertNotNull(actualResponse)
        assertEquals(true, actualResponse.isSuccess)
        actualResponse.getOrNull()?.let { actual ->

            assertEquals("Success", actual.msg)
            assertEquals(200, actual.code)
            assertNotNull(actual.data)
        }
    }
}