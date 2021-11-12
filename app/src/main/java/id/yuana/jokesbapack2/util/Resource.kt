package id.yuana.jokesbapack2.util

data class Resource<out T>(
    val status: Status,
    val throwable: Throwable?,
    val data: T?,
    val message: String?
) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING,
        IDLE
    }

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(
                status = Status.SUCCESS,
                throwable = null,
                data = data,
                message = null
            )
        }

        fun <T> error(message: String, throwable: Throwable? = null, data: T? = null): Resource<T> {
            return Resource(
                status = Status.ERROR,
                throwable = throwable,
                data = data,
                message = message
            )
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(
                status = Status.LOADING,
                throwable = null,
                data = data,
                message = null
            )
        }

        fun <T> idle(): Resource<T> {
            return Resource(
                status = Status.IDLE,
                throwable = null,
                data = null,
                message = null
            )
        }
    }
}