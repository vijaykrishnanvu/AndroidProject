package com.example.digital

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dataBase.MyDB
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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

        var db : MyDB = MyDB.getDataBase(this)
        var h= Handler()
        signUpButton.setOnClickListener {

            var regIntent = Intent(this@MainActivity,Registration1::class.java)
            startActivity(regIntent)
        }
        signInbutton.setOnClickListener {

            GlobalScope.launch {
                var user = db.myDao().checkUserExisting(emailEditText.text.toString(), passEditTExt.text.toString())
               // var editor = sp.edit()
                Log.i("login", "$user")
                if (user.isEmpty()){
                    Log.i("login", "User name doesn't exists")
                    h.post { Toast.makeText(this@MainActivity, "Invalid user name or password", Toast.LENGTH_LONG).show() }

                } else {


                    h.post {
                        Toast.makeText(this@MainActivity, "Welcome ${emailEditText.text.toString()} ", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this@MainActivity, DAshboard::class.java))
                    }
                }
            }


        }
    }
}