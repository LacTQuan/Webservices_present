package com.example.webservices_present

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import java.io.File
import java.io.FileWriter

class SaveFile {
    companion object {
        fun saveObjectsToFile(context: Context, objects: List<Comment>, fileName: String) {
            Log.i("SaveFile", "Saving data to file ${objects.size}")
            val gson = Gson()
            val jsonString = gson.toJson(objects)

            val file = File(context.filesDir, "data.json")
            Log.e("SaveFile", "File path: ${file.absolutePath}")
            FileWriter(file).use { writer ->
                writer.write(jsonString)
            }
        }
    }
}