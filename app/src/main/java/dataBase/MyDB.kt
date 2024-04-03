package dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [MyEntity::class], version = 5)
abstract class MyDB : RoomDatabase() {
    abstract fun myDao() : MyDAOInterface

    companion object {
        @Volatile
        private var INSTANCE: MyDB? = null

        fun getDataBase(context : Context): MyDB {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDB::class.java,
                    "users"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }


}