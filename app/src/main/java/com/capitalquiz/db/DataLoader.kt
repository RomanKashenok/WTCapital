package com.capitalquiz.db

import com.capitalquiz.model.Country
import com.capitalquiz.quiz.QuizDataHolder
import com.capitalquiz.utils.url
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request


class DataLoader {

    fun loadData() {
        val countries = getDataFromUrl(url)
        QuizDataHolder.countries = Gson().fromJson(countries, object : TypeToken<List<Country>>() {}.type)
    }

    private fun getDataFromUrl(url: String): String {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()
        val response = client.newCall(request).execute()
        return response.body()?.string() ?: ""
    }

}