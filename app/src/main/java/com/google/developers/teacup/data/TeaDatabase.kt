package com.google.developers.teacup.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.developers.teacup.R
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader

@Database(entities = [Tea::class], version = 1, exportSchema = false)
abstract class TeaDatabase : RoomDatabase() {

    abstract fun teaDao(): TeaDao

    companion object {

        @Volatile
        private var INSTANCE: TeaDatabase? = null

        /**
         * Returns an instance of Room Database
         *
         * @param context application context
         * @return The singleton TeaDatabase
         */
        fun getInstance(context: Context): TeaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    TeaDatabase::class.java,
                    "tea_database"
                )
                   // .createFromAsset("database/tea_origin.db")
                    .addCallback(StartingDB(context))
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}
class StartingDB(private val context: Context) :RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        fun loadJSONArray(context: Context): JSONArray?{

            val inputStream = context.resources.openRawResource(R.raw.sample_teas)

            BufferedReader(inputStream.reader()).use {
                return JSONArray(it.readText())
            }
        }

        suspend fun fillWithStartingNotes(context: Context){

            val dao = TeaDatabase.getInstance(context)?.teaDao()

            try {
                val notes = loadJSONArray(context)
                if (notes != null){
                    for (i in 0 until notes.length()){
                        val item = notes.getJSONObject(i)

                        val name = item.getString("name")
                        val type = item.getString("type")
                        val origin = item.getString("origin")
                        val steepTimer = item.getLong("steep-time")
                        val description = item.getString("description")
                        val ingredients = item.getString("ingredients")
                        val caffeinelevel = item.getString("caffeine-level")
                        val id:Long=0;

                        val teaEntity = Tea(id,name,type,origin,steepTimer,description,ingredients,caffeinelevel)

                        dao?.insert(teaEntity)
                    }
                }
            }

            catch (e: JSONException) {
                print(e)

            }
        }

    }
}