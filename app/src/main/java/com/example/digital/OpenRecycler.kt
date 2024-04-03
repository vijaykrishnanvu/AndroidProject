package com.example.digital

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class OpenRecycler : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_open_recycler)
        val data = intent.getParcelableExtra<DataClass>("data")
        if(data != null){
            val textView = findViewById<TextView>(R.id.textView)
            val imageView = findViewById<ImageView>(R.id.imageView)
            Glide.with(this).load(data?.thumbnailUrl).into(imageView)

            textView.text = data?.title


        }
    }
}