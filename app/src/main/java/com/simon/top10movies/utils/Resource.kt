package com.simon.top10movies.utils

data class Resource<out T>(val status: Status, val code: Int = 0, val data: T? = null, val message: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> =
            Resource(status = Status.SUCCESS, data = data, message = null)

        fun <T> error(code: Int, data: T? = null, message: String = ""): Resource<T> =
            Resource(status = Status.ERROR, data = data, message = message)
    }
}