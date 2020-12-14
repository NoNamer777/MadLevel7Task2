package com.nonamer777.madlevel7task2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.nonamer777.madlevel7task2.R
import com.nonamer777.madlevel7task2.databinding.FragmentQuizBinding

/**
 * A [Fragment] subclass where users can view and answer the questions of the quiz.
 */
class QuizFragment: Fragment() {

    private lateinit var binding: FragmentQuizBinding

    private val questionViewModel: QuestionViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuizBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        questionViewModel.getQuestions()
    }
}
