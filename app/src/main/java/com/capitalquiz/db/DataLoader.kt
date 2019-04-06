package com.capitalquiz.db

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.Request

class DataLoader {

    fun loadData(context: Context) {

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