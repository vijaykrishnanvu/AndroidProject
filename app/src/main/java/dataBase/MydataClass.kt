package dataBase

import androidx.room.RoomDatabase
import androidx.room.Database
@Database(entities = [MyEntity::class],version = 1)
abstract class MydataClass : RoomDatabase(){
    // Declare an abstract function to provide access to DAO (Data Access Object)
    public abstract  fun myDao() : MyDAOInterface
}