package com.example.shayariapp

import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.ClipboardManager
import android.content.ContentValues.TAG
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shayariapp.databinding.ItemShayariBinding


class ShayariAdapter(
    val allShayariActivity: AllShayariActivity,
    val shayari: ArrayList<ShayariModel>
) :
    RecyclerView.Adapter<ShayariAdapter.ShayariViewHolder>() {
    class ShayariViewHolder(val binding: ItemShayariBinding) : RecyclerView.ViewHolder(binding.root)

    val gradientList = arrayListOf<Int>(
        R.drawable.gradient_1,
        R.drawable.gradient_2,
        R.drawable.gradient_3,
        R.drawable.gradient_4,
        R.drawable.gradient_5
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShayariViewHolder {
        return ShayariViewHolder(
            ItemShayariBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = shayari.size

    override fun onBindViewHolder(holder: ShayariViewHolder, position: Int) {

        holder.binding.itemShayari.setBackgroundResource(gradientList[position % 5])

        holder.binding.shareBtn.setOnClickListener {
            try {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Hindi Shayari App")
                val shareMessage = "Check out this cool shayari: \n ${shayari[position].data.toString()} \n\n"
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                allShayariActivity.startActivity(Intent.createChooser(shareIntent, "Choose one"))
            } catch (e: Exception) {
                //e.toString();
            }
        }

        holder.binding.copyBtn.setOnClickListener {
            val clipboard: ClipboardManager? =
                allShayariActivity.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager?
            val clip = ClipData.newPlainText("label", shayari[position].data.toString())
            clipboard?.setPrimaryClip(clip)
        }

        holder.binding.wharsappBtn.setOnClickListener {
            val whatsappIntent = Intent(Intent.ACTION_SEND)
            whatsappIntent.type = "text/plain"
            whatsappIntent.setPackage("com.whatsapp")
            whatsappIntent.putExtra(Intent.EXTRA_TEXT, "Check out this cool Shayari I found: \n ${shayari[position].data.toString()}\n")
            try {
                allShayariActivity.startActivity(whatsappIntent)
            } catch (ex: ActivityNotFoundException) {
                Log.e(TAG,ex.message.toString())
            }
        }

        holder.binding.itemShayari.text = shayari[position].data.toString()
    }
}