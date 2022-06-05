package upc.pe.recycleappupc.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import upc.pe.recycleappupc.dao.AlarmDAO
import upc.pe.recycleappupc.models.AlarmRoom

@Database(entities = arrayOf(AlarmRoom::class), version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getDAO(): AlarmDAO

    companion object {

        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            if (INSTANCE == null) {
                INSTANCE = Room
                    .databaseBuilder(context, AppDataBase::class.java, "app3.db")
                    .allowMainThreadQueries()
                    .build()
            }

            return INSTANCE as AppDataBase
        }


    }
}