package dataBase

import androidx.room.ColumnInfo


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MyEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_column")
    var myid : Int = 0

    // Define column for storing name
    @ColumnInfo(name = "name_column")
    var myEmail : String = ""

    // Define column for storing password
    @ColumnInfo(name = "password_column")
    var mypassword : String  = ""

}