package com.nonamer777.madlevel7task2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.nonamer777.madlevel7task2.R
import com.nonamer777.madlevel7task2.databinding.FragmentQuizBinding
import com.nonamer777.madlevel7task2.model.Question

/**
 * A [Fragment] subclass where users can view and answer the questions of the quiz.
 */
class QuizFragment: Fragment() {

    private lateinit var binding: FragmentQuizBinding

    private val questionViewModel: QuestionViewModel by activityViewModels()

    private val questions = arrayListOf<Question>()

    private var currentIndex = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuizBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeQuestions()

        questionViewModel.getQuestions()

        binding.btnConfirmAnswer.setOnClickListener { onCheckAnswer() }
    }

    private fun observeQuestions() {
        questionViewModel.error.observe(viewLifecycleOwner, {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        })

        questionViewModel.questions.observe(viewLifecycleOwner, {
            currentIndex = -1

            questions.clear()
            questions.addAll(it)

            updateUI()
        })
    }

    private fun updateUI() {
        binding.labelQuestionTracker.text = getString(
            R.string.label_question_tracker_text,
            (++currentIndex + 1),
            questions.size
        )

        val currentQuestion = questions[currentIndex]

        binding.labelQuestion.text = currentQuestion.question

        if (currentQuestion.options != null) {
            val options = currentQuestion.options.shuffled()

            binding.radioBtnOption1.text = options[0]
            binding.radioBtnOption2.text = options[1]
            binding.radioBtnOption3.text = options[2]
        }
    }

    private fun onCheckAnswer() {
        val selectedOption = binding.selectableOptionsGroup.checkedRadioButtonId

        if (selectedOption == -1) {
            showMessage(getString(R.string.notification_message_no_option_selected))

            return
        }

        if (activity?.findViewById<RadioButton>(selectedOption)?.text != questions[currentIndex].answer) {
            showMessage(getString(R.string.notification_message_wrong_answer))

            return
        }

        binding.selectableOptionsGroup.clearCheck()

        if (currentIndex == questions.size - 1) {
            showMessage(getString(R.string.notification_message_quiz_completed))

            findNavController().popBackStack()

            return
        }

        showMessage(getString(R.string.notification_message_correct_answer))
        updateUI()
    }

    private fun showMessage(message: String) = Toast.makeText(
        activity,
        message,
        Toast.LENGTH_SHORT
    ).show()
}
