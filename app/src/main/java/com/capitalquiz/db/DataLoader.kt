package com.capitalquiz.db

import android.os.AsyncTask
import com.capitalquiz.model.Country
import com.capitalquiz.quiz.QuizDataHolder
import com.capitalquiz.utils.url
import com.capitalquiz.utils.url2
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request


class DataLoader: AsyncTask<Unit, Unit, String>() {

    override fun doInBackground(vararg params: Unit?): String {
        return getDataFromUrl(url)
    }

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

    override fun onPostExecute(result: String) {
        QuizDataHolder.countries = ArrayList(Gson().fromJson(result, Array<Country>::class.java).toList())
    }
}