package com.beinny.android.arfurnitureapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var ARButton: Button
    private lateinit var ImageClassifierButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ARButton = findViewById(R.id.ar_btn)
        ImageClassifierButton = findViewById(R.id.image_classifier_btn)

        ImageClassifierButton.setOnClickListener { view ->
            val intent = Intent(this,ImageClassifierActivity::class.java)
            startActivity(intent)
        }
    }
}