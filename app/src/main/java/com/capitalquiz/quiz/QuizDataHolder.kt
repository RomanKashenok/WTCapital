package com.capitalquiz.quiz

import android.app.Activity
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.capitalquiz.R
import com.capitalquiz.model.Country
import kotlin.random.Random

object QuizDataHolder {

    var gamesLimit = 20
    var currentGameNumber = 0
    private var capitalsMap = HashMap<String, String>()
    private var currentCountry : String = String()
    private var countries = ArrayList<Country>()
    private var capitals = ArrayList<String>()


    fun fillTheButtons(context : Activity, buttons : ArrayList<View>){
        if(buttons.size != 4){
            fillButtonsManually(context, buttons)
        }

        do {
            currentCountry = countries[Random.nextInt(0, countries.size-1)]
        }while (AnswerChecker.correctAnswers[currentCountry] != null)

        val rightCapital : String = capitalsMap[currentCountry.orEmpty()].toString()
        var fourCapitals = HashSet<String>()
        fourCapitals.add(rightCapital)
        while (fourCapitals.size != 4){
            fourCapitals.add(capitals[Random.nextInt(0, capitals.size-1)])
        }
        val shuffled = fourCapitals.toList().shuffled()

        context.findViewById<TextView>(R.id.questionTextId).text = currentCountry
        if(buttons.size == 4 && fourCapitals.size ==4){
            for(i in 0 until buttons.size){
                (buttons[i] as Button).text = shuffled[i]
                (buttons[i] as Button).isEnabled = true
                (buttons[i] as Button).setBackgroundResource(R.color.light_grey_font)
            }
        }
    }

    private fun fillButtonsManually(context : Activity, buttons: ArrayList<View>) {
        for (i in 1..4){
            val id = context.resources.getIdentifier("choise${i}Id", "id", context.packageName)
            buttons[i-1] = context.findViewById<View>(id) as Button
        }
    }
}