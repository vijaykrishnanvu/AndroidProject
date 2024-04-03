package com.example.digital

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import dataBase.MyDB
import dataBase.MyEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class Registration1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registration1)
        var nameEdit = findViewById<EditText>(R.id.editTextName)
        var passEdit = findViewById<EditText>(R.id.editTextPassword)
        var confirmPassEdit = findViewById<EditText>(R.id.editTextConfirmPassword)
        var emailEdit = findViewById<EditText>(R.id.editTextEmailAddress)
        var phoneEdit = findViewById<EditText>(R.id.editTextPhone)
        var regSubmitButton = findViewById<Button>(R.id.Submitbutton)

        var db : MyDB = MyDB.getDataBase(this)
        var h = Handler()

        regSubmitButton.setOnClickListener {
            val name = nameEdit.text.toString()
            val email = emailEdit.text.toString()
            val mobile = phoneEdit.text.toString()
            val password = passEdit.text.toString()
            val confirmPassword = confirmPassEdit.text.toString()
            if (password.equals(confirmPassword)) {
                GlobalScope.launch {
                    var users = MyEntity()
                    users.myEmail = emailEdit.text.toString()
                    users.mypassword = passEdit.text.toString()
                    db.myDao().saveData(users)
                }
                Toast.makeText(this, "Registered Successfully", Toast.LENGTH_LONG).show()
                finish()
                GlobalScope.launch {
                    db.myDao().readData().forEach{

                        var Email = "${it.myEmail}"
                        var passWord = "${it.mypassword}"
                        // Check if username and password match with database records
                        h.post {
                            //Toast.makeText(this@Registration1, "$Email Password $passWord", Toast.LENGTH_SHORT).show()
                        }

                    }
                }
                // saveSignUpData(name, email, password, mobile)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                // Navigate to sign-in screen or perform other actions
                // For example:
                // startActivity(Intent(this, LoginActivity::class.java))
            } else {
                // Passwords don't match, show error message
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            }
        }

    }








}