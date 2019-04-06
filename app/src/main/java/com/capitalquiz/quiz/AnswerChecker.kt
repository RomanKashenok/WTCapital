package com.capitalquiz.quiz

object AnswerChecker {
    fun checkAnswer(answer: String) : Boolean {
        return answer == CapitalsFiller.currentCountry?.capital
    }
}