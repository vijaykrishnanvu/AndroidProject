package dataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
    interface MyDAOInterface {
        @Insert(onConflict = OnConflictStrategy.IGNORE)
        fun saveData(myEntity : MyEntity)

        // Define a function to read data from the database
        @Query("select * from MyEntity")
        fun readData() : List<MyEntity>
    @Query("select * from MyEntity where name_column like :user and password_column like :pass")
    fun checkUserExisting(user : String, pass : String) : List<MyEntity>



    }