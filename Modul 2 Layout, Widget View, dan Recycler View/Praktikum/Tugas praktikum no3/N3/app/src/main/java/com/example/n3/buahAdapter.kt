package com.example.n3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class buahAdapter(private val context: Context, val buah: List<buah>, val listener: (buah) -> Unit)
    : RecyclerView.Adapter<buahAdapter.buahViewHolder>(){

    class buahViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val imgbuah = view.findViewById<ImageView>(R.id.img_item_photo)
        val namebuah = view.findViewById<TextView>(R.id.tv_item_name)
        val descbuah = view.findViewById<TextView>(R.id.tv_item_description)

        fun bindView(buah: buah, listener: (buah) -> Unit){
            imgbuah.setImageResource(buah.imgbuah)
            namebuah.text = buah.namebuah
            descbuah.text = buah.descbuah
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): buahViewHolder {
        return buahViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_buah, parent, false)
        )
    }

    override fun onBindViewHolder(holder: buahViewHolder, position: Int) {
        holder.bindView(buah[position], listener)
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, `"Macam-macam Buah", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = buah.size
    }

