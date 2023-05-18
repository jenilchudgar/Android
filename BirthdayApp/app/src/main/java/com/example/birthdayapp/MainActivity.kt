package com.example.birthdayapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.buttonSubmit)
        val editText = findViewById<EditText>(R.id.editText)

        btn.setOnClickListener {
            val name: String = editText.editableText.toString()
            val intent = Intent(this,BirthdayGreetingActivity::class.java)
            intent.putExtra("name",name)
            startActivity(intent)
        }

    }
}