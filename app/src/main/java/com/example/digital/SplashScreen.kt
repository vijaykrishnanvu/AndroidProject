package com.example.digital

import android.content.Intent
import android.os.Bundle
import kotlin.concurrent.thread
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
//import kotlinx.coroutines.DefaultExecutor.thread

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_plash_screen)

        thread {
            Thread.sleep(6000)
            var myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
        }


    }
}
