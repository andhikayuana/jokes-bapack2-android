package id.yuana.jokesbapack2.util

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import org.junit.Test

class ResourceTest {

    @Test
    fun `create resource success`() {

        //given
        val dummyData = listOf(
            "senin",
            "selasa",
            "rabu",
            "kamis",
            "jumat",
            "sabtu",
            "minggu"
        )

        //when
        val actual = Resource.success(dummyData)

        //then
        assertEquals(Resource.Status.SUCCESS, actual.status)
        assertEquals(dummyData, actual.data)
        assertNull(actual.throwable)
        assertNull(actual.message)
    }

    @Test
    fun `create resource error`() {
        //given
        val dummyThrowable = Throwable("Random throwable")
        val dummyErrorMessage = "This is error"

        //when
        val actual = Resource.error<String>(dummyErrorMessage, dummyThrowable)

        //then
        assertEquals(Resource.Status.ERROR, actual.status)
        assertEquals(dummyErrorMessage, actual.message)
        assertEquals(dummyThrowable, actual.throwable)
        assertNull(actual.data)
    }

    @Test
    fun `create resource loading`() {
        //given

        //when
        val actual = Resource.loading<String>()

        //then
        assertEquals(Resource.Status.LOADING, actual.status)
        assertNull(actual.message)
        assertNull(actual.throwable)
        assertNull(actual.data)
    }

    @Test
    fun `create resource idle`() {
        //given

        //when
        val actual = Resource.idle<String>()

        //then
        assertEquals(Resource.Status.IDLE, actual.status)
        assertNull(actual.message)
        assertNull(actual.throwable)
        assertNull(actual.data)
    }
}