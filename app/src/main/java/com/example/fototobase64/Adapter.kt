package com.example.fototobase64

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(val list: List<Foto>): RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Foto){
            view.apply {
                val idTV = findViewById<TextView>(R.id.idTV)
                val imageView = findViewById<ImageView>(R.id.imageIV)
                idTV.text = item.id.toString()
                imageView.setImageBitmap(decodeImage(item.base64))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(holder){
            is ViewHolder -> holder.bind(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


}