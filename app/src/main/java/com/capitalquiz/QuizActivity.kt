package com.capitalquiz

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.capitalquiz.quiz.CapitalsFiller
import com.capitalquiz.quiz.QuizDataHolder
import com.capitalquiz.utils.Constants.ANSWER_DELAY
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, QuizActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        questionText.text = getString(R.string.question_text)
        val allButtons = buttonsContainerId.touchables
        CapitalsFiller.fillTheButtons(allButtons, question)

        assignListenersToButtons(allButtons)
    }

    private fun assignListenersToButtons(allButtons: ArrayList<View>) {
        for (button in allButtons) {
            button.setOnClickListener { b ->
                allButtons.forEach { but -> (but as Button).isEnabled = false }
                CapitalsFiller.isChecked = true
                val answer = (b as Button).text.toString()

                if(answer == CapitalsFiller.currentCountry?.capital) {
                    b.setBackgroundResource(R.color.button_answer_correct)
                    QuizDataHolder.correctAnswers++
                } else {
                    b.setBackgroundResource(R.color.button_answer_incorrect)
                    Toast.makeText(this, CapitalsFiller.currentCountry?.capital, Toast.LENGTH_LONG).show()
                }

                Handler().postDelayed({
                    CapitalsFiller.fillTheButtons(allButtons, question)
                }, ANSWER_DELAY)
            }
        }

    }
}
