package com.capitalquiz.quiz

import android.view.View
import android.widget.Button
import android.widget.TextView
import com.capitalquiz.R
import com.capitalquiz.model.Country
import kotlin.random.Random

object CapitalsFiller {

    var isChecked = false
    var currentCountry: Country? = null
    private var tempCapitals = ArrayList<String>()

    fun fillTheButtons(buttons: ArrayList<View>, countryName: TextView) {
        if(isChecked) {
            tempCapitals.clear()
            uncheckAllButtons(buttons)
        }
        currentCountry = getRandomCountry()

        currentCountry?.let {country ->
            countryName.text = country.name
            tempCapitals.add(country.capital)

            for(i in 0 until buttons.size - 1) {
                var tempCountry: Country
                do {tempCountry = getRandomCountry()} while (tempCountry == currentCountry || tempCapitals.contains(tempCountry.capital))
                tempCapitals.add(tempCountry.capital)
            }
            tempCapitals.shuffle()

            for (i in 0 until buttons.size) {
                (buttons[i] as Button).text = tempCapitals[i]
            }
        }
    }

    private fun getRandomCountry(): Country {
        return QuizDataHolder.countries[Random.nextInt(QuizDataHolder.countries.size)]
    }

    private fun uncheckAllButtons(buttons: ArrayList<View>) {
        buttons.forEach { but ->
            run {
                but.setBackgroundResource(R.color.button_color_default)
                (but as Button).isEnabled = true
            }
        }
    }

}