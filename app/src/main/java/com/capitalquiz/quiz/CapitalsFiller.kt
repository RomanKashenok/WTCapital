package com.capitalquiz.quiz

import android.view.View
import android.widget.Button
import com.capitalquiz.model.Country
import kotlin.random.Random

object CapitalsFiller {

    var currentCountry: Country? = null
    private var tempCapitals = ArrayList<String>()

    fun fillTheButtons(buttons: ArrayList<View>) {
        currentCountry = getRandomCountry()
        currentCountry?.let {country ->
            tempCapitals.add(country.capital)

            buttons.forEach {
                insertCountryCapital(it)
            }
        }
    }

    private fun insertCountryCapital(button: View) {
        var tempCountry: Country
        do {tempCountry = getRandomCountry()} while (tempCountry == currentCountry)

        (button as Button).text = tempCountry.capital
    }

    private fun getRandomCountry(): Country {
        return QuizDataHolder.countries[Random.nextInt(QuizDataHolder.countries.size)]
    }

}