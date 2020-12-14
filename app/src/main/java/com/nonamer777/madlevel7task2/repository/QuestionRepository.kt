package com.nonamer777.madlevel7task2.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.nonamer777.madlevel7task2.model.Question
import com.nonamer777.madlevel7task2.repository.exception.QuestionRetrievalException
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeout

class QuestionRepository {

    private val firestore = FirebaseFirestore.getInstance()

    private val questionsCollection = firestore.collection("questions")

    private val _questions = MutableLiveData<List<Question>>()

    val questions: LiveData<List<Question>> get() = _questions

    suspend fun getQuestions() = try { withTimeout(5_000) {
        val data = questionsCollection.get().await()
        val questions = arrayListOf<Question>()

        for (questionObj in data) {
            questions.add(questionObj.toObject())
        }

    }} catch (exception: Exception) {
        throw QuestionRetrievalException("Retrieval-firebase-task has failed")
    }
}
