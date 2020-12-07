package com.photos

import android.os.AsyncTask
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class HttpService() : AsyncTask<Void?, Void?, List<Photo>>() {
    override fun doInBackground(vararg params: Void?): List<Photo> {
        val resposta = StringBuilder()
        try {
            val url = URL("https://jsonplaceholder.typicode.com/photos")

            val http = url.openConnection() as HttpURLConnection
            http.requestMethod = "GET"
            http.setRequestProperty("Content-type", "application/json");
            http.setRequestProperty("Accept", "application/json");
            if (http.responseCode == HttpURLConnection.HTTP_OK) {
                try {
                    val stream = BufferedInputStream(http.inputStream)
                    val bufferedReader = BufferedReader(InputStreamReader(stream))
                    bufferedReader.forEachLine { resposta.append(it) }
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    http.disconnect()
                }
            } else {
                println("ERROR ${http.responseCode}")
            }
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val sType = object : TypeToken<List<Photo>>() { }.type
        return Gson().fromJson<List<Photo>>(resposta.toString(), sType)
    }
}
