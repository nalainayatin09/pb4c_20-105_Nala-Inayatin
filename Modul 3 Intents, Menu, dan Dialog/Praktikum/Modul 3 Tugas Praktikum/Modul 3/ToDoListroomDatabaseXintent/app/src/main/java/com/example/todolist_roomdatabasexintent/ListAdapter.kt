package com.example.todolist_roomdatabasexintent

import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.util.findColumnIndexBySuffix
import com.example.todolist_roomdatabasexintent.room.Note

class ListAdapter(private val notes: ArrayList<Note>, private var listener: OnAdapterListener)
    : RecyclerView.Adapter<ListAdapter.NoteViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_list, parent, false)
        )
    }

    override fun getItemCount() = notes.size


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.view.findViewById<TextView>(R.id.text_title).setText(note.title)

        holder.view.findViewById<TextView>(R.id.text_title).setOnClickListener {
            listener.onClick( note )
        }

        holder.view.findViewById<ImageView>(R.id.icon_edit).setOnClickListener {
            listener.onUpdate( note )
        }

        holder.view.findViewById<ImageView>(R.id.icon_delete).setOnClickListener {
            listener.onDelete( note )
        }

    }

    class NoteViewHolder(val view : View): RecyclerView.ViewHolder(view)

    fun setData(list: List<Note>){
        notes.clear()
        notes.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener{
        fun onClick(note: Note)
        fun onUpdate(note: Note)
        fun onDelete(note: Note)
    }
}