package com.example.shayariapp

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shayariapp.databinding.ActivityAllShayariBinding
import com.google.firebase.firestore.FirebaseFirestore

class AllShayariActivity : AppCompatActivity() {

    lateinit var binding: ActivityAllShayariBinding
    lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllShayariBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra("ID")
        val name = intent.getStringExtra("name")

        db = FirebaseFirestore.getInstance()

        Log.d(TAG,"The id is $id and the name is $name")

        binding.backBtn.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.catName.text = name

        db.collection("Shayari").document(id!!).collection("all")
            .addSnapshotListener { value, error ->
                val shayari = arrayListOf<ShayariModel>()
                val data = value?.toObjects(ShayariModel::class.java)
                shayari.addAll(data!!)

                binding.recyclerViewAllShayari.layoutManager = LinearLayoutManager(this)
                binding.recyclerViewAllShayari.adapter = ShayariAdapter(this,shayari)
            }
    }
}