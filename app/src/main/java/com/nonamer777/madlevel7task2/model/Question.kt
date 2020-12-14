package com.nonamer777.madlevel7task2.model

data class Question(

    val question: String,

    val answer: String,

    val options: List<String>,

    val id: String? = null
)
