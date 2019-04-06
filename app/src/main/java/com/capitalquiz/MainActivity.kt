package com.capitalquiz

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.capitalquiz.quiz.QuizDataHolder.gamesLimit
import kotlinx.android.synthetic.main.activity_count.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count)

        val enterButton = entryBtn

        enterButton.setOnClickListener {
            val gamesNumber = gamesCountEditText.text.toString()
            if (gamesNumber.isEmpty() || gamesNumber == null) {
                Toast.makeText(this, getText(R.string.games_number_exception), Toast.LENGTH_SHORT).show()
            } else {
                gamesLimit = Integer.parseInt(gamesNumber)
                startActivity(QuizActivity.getIntent(this))
            }
        }
    }
}
