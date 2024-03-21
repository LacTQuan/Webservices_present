package com.example.webservices_present

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val http = Http(this, findViewById<ProgressBar>(R.id.progressBar))
        val context = this

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            // Launch a coroutine to fetch data asynchronously
            CoroutineScope(Dispatchers.Main).launch { // Use CoroutineScope with Main dispatcher for UI-related coroutines
                val url = "https://jsonplaceholder.typicode.com/comments/"
                val jsonResponse = http.fetchData(url)
//                val jsonResponse = MyRetrofit(findViewById<ProgressBar>(R.id.progressBar)).fetchData(url)
                Log.i("MainActivity", "response:$jsonResponse")
            }
        }
    }
}