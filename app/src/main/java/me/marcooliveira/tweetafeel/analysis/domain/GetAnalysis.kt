package me.marcooliveira.tweetafeel.analysis.domain

import me.marcooliveira.tweetafeel.core.Either
import me.marcooliveira.tweetafeel.core.Error

class GetAnalysis {

    suspend fun execute(userId: String): Either<Error, String> {
        return try {
            Either.Value("")
        } catch (e: Exception) {
            Either.Error(Error(e.message))
        }
    }
}