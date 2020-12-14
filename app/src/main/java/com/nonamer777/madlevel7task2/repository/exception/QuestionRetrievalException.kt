package com.nonamer777.madlevel7task2.repository.exception

import java.lang.Exception

data class QuestionRetrievalException(override val message: String): Exception(message)
