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
    var countries = ArrayList<Country>()
    private var capitals = ArrayList<String>()

}