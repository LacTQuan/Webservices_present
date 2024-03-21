package com.example.webservices_present

import android.content.Context
import android.util.Log
import android.widget.ProgressBar
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class Http(private val context: Context, private val progressBar: ProgressBar) {

    var jsonResponse = ""

    suspend fun fetchData(urlString: String): List<Comment> { // The suspend keyword in Kotlin is used to define a function that can be executed asynchronously within a coroutine.
        var comments = emptyList<Comment>()
        withContext(Dispatchers.IO) {// Dispatchers.IO ensures that the code runs on a different thread / asynchronously
            try {
                // Show progress bar
                progressBar.post { progressBar.visibility = ProgressBar.VISIBLE }

                // STEP1. Create a HttpURLConnection object for GET method
                val url = URL(urlString)
                val urlConnection = url.openConnection() as HttpURLConnection
                urlConnection.requestMethod = "GET"
                // True-indicates that the app will read data from the URL connection
                urlConnection.doInput = true
                urlConnection.connect()
                // STEP2. Wait for incoming RESPONSE stream, place data in a buffer
                val isResponse = urlConnection.inputStream
                val responseBuffer = BufferedReader(InputStreamReader(isResponse))
                // STEP3. Arriving JSON fragments are concatenated into a StringBuilder
                val strBuilder = StringBuilder()
                var myLine: String? = responseBuffer.readLine()
                while (myLine != null) {
                    strBuilder.append(myLine)
                    myLine = responseBuffer.readLine()
                }
                jsonResponse = strBuilder.toString()
                Log.i("HttpUrl", "response:$jsonResponse")
                // Close the connection
                urlConnection.disconnect()
                if (jsonResponse.isNotEmpty()) {
                    // Use mapper to convert JSON to Kotlin object
                    val mapper = jacksonObjectMapper()
                    comments = mapper.readValue(jsonResponse, Array<Comment>::class.java).toList()
                } else {
                    Log.e("Error", "Empty JSON response received")
                }
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
                // Return error message
                return@withContext "Error: ${e.message}"
            } finally {
                // Hide progress bar
                progressBar.post { progressBar.visibility = ProgressBar.GONE }
            }
        }
        // Return the JSON response
        return comments
    }
}

