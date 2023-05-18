package com.example.memeshareapp

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG: String = "LOAD_STATUS"
        const val URL: String = "https://meme-api.com/gimme"
    }

    var currentImageURL: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        load()
    }

    private fun load(){
        // Define Views
        val img = findViewById<ImageView>(R.id.imageView)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        // Set Visibility of Progress Bar
        progressBar.visibility = View.VISIBLE

        // Request a string response from the provided URL.
        val jsonObject = JsonObjectRequest(
            Request.Method.GET, URL,null,
            {response ->
                Log.d(TAG,"That worked!")

                currentImageURL = response.getString("url")
                Glide.with(this).load(currentImageURL).listener(object: RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }

                }).into(img)

            },
            {
                Log.e(TAG,"That didn't Work!")
            }
        )

        // Add the request to the RequestQueue.
        MySingleton.getInstance(this).addToRequestQueue(jsonObject)
    }

    fun nextMeme(view: View){
        load()
    }

    fun shareMeme(view: View){
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_TEXT,"Hey! Check out this Cool Meme, I got from Reddit: $currentImageURL")
        intent.type = "text/plain"
        val chooser = Intent.createChooser(intent,"Share this M eme using...")
        startActivity(chooser)
    }
}