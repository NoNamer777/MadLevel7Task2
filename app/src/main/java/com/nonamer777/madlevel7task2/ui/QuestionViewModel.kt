package com.nonamer777.madlevel7task2.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nonamer777.madlevel7task2.model.Question
import com.nonamer777.madlevel7task2.repository.QuestionRepository
import com.nonamer777.madlevel7task2.repository.exception.QuestionRetrievalException
import kotlinx.coroutines.launch

class QuestionViewModel(application: Application): AndroidViewModel(application) {

    private val TAG = "FIRESTORE"

    private val questionRepo = QuestionRepository()

    val questions: LiveData<List<Question>> = questionRepo.questions

    private val _error = MutableLiveData<String>()

    val error: LiveData<String> get() = _error

    fun getQuestions() = viewModelScope.launch {
        try { questionRepo.getQuestions() } catch (exception: QuestionRetrievalException) {
            val message = "Something went wrong while retrieving the questions"

            Log.e(TAG, exception.message ?: message)

            _error.value = message
        }
    }
}
