package me.marcooliveira.tweetafeel.core

sealed class Either<out E, out V> {
    data class Error<out E>(val error: E) : Either<E, Nothing>()
    data class Value<out V>(val value: V) : Either<Nothing, V>()

    fun either(fnL: (E) -> Any, fnR: (V) -> Any): Any =
        when (this) {
            is Error -> fnL(error)
            is Value -> fnR(value)
        }
}