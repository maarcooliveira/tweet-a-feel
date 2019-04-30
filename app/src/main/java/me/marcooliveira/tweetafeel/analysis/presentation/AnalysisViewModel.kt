package me.marcooliveira.tweetafeel.analysis.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.marcooliveira.tweetafeel.analysis.domain.GetAnalysis
import me.marcooliveira.tweetafeel.core.Error

class AnalysisViewModel(application: Application): AndroidViewModel(application) {

    private val getAnalysis = GetAnalysis()

    internal val error = MutableLiveData<String?>()

    fun analyseTweet(tweet: String) {
        viewModelScope.launch {
            val result = getAnalysis.execute(tweet)

            withContext(Dispatchers.Main) {
                result.either(::handleError, ::handleAnalysis)
            }
        }
    }

    private fun handleAnalysis(result: String) {

    }

    private fun handleError(errorResponse: Error?) {
        error.postValue(errorResponse?.message)
    }
}