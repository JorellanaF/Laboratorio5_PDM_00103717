package com.example.pokedex

import android.net.Uri
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*

class NetworkUtils {
    val MOVIES_APÍ_BASEURL = "https://pokeapi.co/api/v2/"
    val pokemon: String = "pokemon/"

    fun buildSearchUrl(movieName: String): URL {
        val builtUri = Uri.parse(MOVIES_APÍ_BASEURL)
            .buildUpon()
            .appendQueryParameter("apikey", pokemon)
            .appendQueryParameter("t", movieName)
            .build()

        return try {
            URL(builtUri.toString())
        }catch (e : MalformedURLException){
            URL("")
        }
    }

    @Throws(IOException::class)
    fun getResponseFromHttpUrl(url: URL): String{
        val urlConnection = url.openConnection() as HttpURLConnection
        try{
            val `in` = urlConnection.inputStream
            val scanner = Scanner(`in`)
            scanner.useDelimiter("\\A")

            val hasInput = scanner.hasNext()
            return if (hasInput){
                scanner.next()
            }else{
                ""
            }

        }finally {
            urlConnection.disconnect()
        }
    }
}