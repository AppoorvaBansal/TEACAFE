package com.google.developers.teacup.data

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.developers.teacup.R
import com.google.developers.teacup.util.executeThread
import java.io.BufferedReader
import org.json.JSONArray
import org.json.JSONException

class StartingTeas(private val context: Context) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) = executeThread {
        fillWithStartingData(context)
    }

    @WorkerThread
    private fun fillWithStartingData(context: Context) {
        val dao = TeaDatabase.getInstance(context).teaDao()

        try {
            val teas = loadJsonArray(context)
            if (teas != null) {
                for (i in 0 until teas.length()) {
                    val item = teas.getJSONObject(i)
                    val name = item.getString("name")
                    val type = item.getString("type")
                    val origin = item.getString("origin")
                    val steep = item.getLong("steep-time")
                    val description = item.getString("description")
                    val ingredients = item.getString("ingredients")
                    val caffeine = item.getString("caffeine-level")

                    val tea = Tea(
                        name = name,
                        type = type,
                        origin = origin,
                        steepTimeMs = steep,
                        description = description,
                        ingredients = ingredients,
                        caffeineLevel = caffeine
                    )
                    dao.insert(tea)
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun loadJsonArray(context: Context): JSONArray? {
        val inputStream = context.resources.openRawResource(R.raw.sample_teas)
        BufferedReader(inputStream.reader()).use {
            return JSONArray(it.readText())
        }
    }
}
