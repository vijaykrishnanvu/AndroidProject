package com.example.digital

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dataBase.MyDB
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        var signUpButton = findViewById<TextView>(R.id.text_signUp)
        var emailEditText = findViewById<EditText>(R.id.email_edit)
        var passEditTExt = findViewById<EditText>(R.id.edit_Password)
        var signInbutton = findViewById<Button>(R.id.signIn_button)
        var backbutton = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        var welMsg = findViewById<TextView>(R.id.welcomeMsg)

        //var db = Room.databaseBuilder(this, MyDB::class.java,"mydatabase")
           // .fallbackToDestructiveMigration().build()
        var db : MyDB = MyDB.getDataBase(this)
        var h= Handler()
        signUpButton.setOnClickListener {

                var regIntent = Intent(this@MainActivity,Registration1::class.java)
            startActivity(regIntent)
        }
signInbutton.setOnClickListener {8

                                        var myemail = emailEditText.text.toString()
                                        var mypass = passEditTExt.text.toString()
    thread {
        db.myDao().readData().forEach{
            h.post {
                var Email = "${it.myEmail}"
                var passWord = "${it.mypassword}"
                // Check if username and password match with database records
                if ((Email.equals(myemail)) && (passWord.equals(mypass))) {
                    Toast.makeText(this, "Incorrect Password", Toast.LENGTH_SHORT).show()
                    var myIntent = Intent(this, DAshboard::class.java)
                    startActivity(myIntent)
                }
            }
        }
    }
        /*val name = intent.getStringExtra("name")
        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")
        val phone = intent.getStringExtra("phone")
        */
        /*
        if(myemail .equals(email)  && mypass .equals(password) ){
            var  DashIntent = Intent(this@MainActivity,DAshboard::class.java)
            startActivity(DashIntent)
        }
            else
        {
            Toast.makeText(this, "Incorrect Password", Toast.LENGTH_SHORT).show()
        }
        */

    }
    }
}