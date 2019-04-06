package com.capitalquiz.db

import android.os.AsyncTask
import com.capitalquiz.App
import com.capitalquiz.R
import com.capitalquiz.model.Country
import com.capitalquiz.quiz.QuizDataHolder
import com.capitalquiz.utils.url
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.InputStreamReader


class DataLoader: AsyncTask<Unit, Unit, String>() {

    override fun doInBackground(vararg params: Unit?): String {
        return getDataFromUrl(url)
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
        if (QuizDataHolder.countries.size < 100) {
            loadDataFromRaw()
        }
    }

    private fun loadDataFromRaw() {
        val context = App.instance.applicationContext
        val filename = context.getString(R.string.capitals)
        val resource =
            context.resources.openRawResource(context.resources.getIdentifier(filename, "raw", context.packageName))
        resource.use {
            val reader = BufferedReader(InputStreamReader(resource))
            var line: String? = reader.readLine()
            var index = 1
            while (line != null) {
                val keyValuePair = line.split("--".toRegex())
                val country = Country(index, keyValuePair[0], keyValuePair[1])
                index++
                line = reader.readLine()
                QuizDataHolder.countries.add(country)
            }
            resource.close()
        }
    }
}