package com.example.todolist_roomdatabasexintent

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.todolist_roomdatabasexintent.room.Constant
import com.example.todolist_roomdatabasexintent.room.Note
import com.example.todolist_roomdatabasexintent.room.NoteDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
class EditActivity : AppCompatActivity() {


    private lateinit var btnSave : Button
    private lateinit var btnUpdate : Button
    private lateinit var editTitle : EditText
    private lateinit var editNote : EditText


    val db by lazy {NoteDb(this)}
    private var noteId: Int = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        btnSave = findViewById(R.id.button_save)
        btnUpdate = findViewById(R.id.button_update)
        editTitle = findViewById(R.id.edit_title)
        editNote = findViewById(R.id.edit_note)

        setUpView()

        btnSave.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.listDao().addList(
                    Note(0, editTitle.text.toString(), editNote.text.toString())
                )
                finish()
            }
        }
        btnUpdate.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.listDao().updateList(
                    Note(noteId, editTitle.text.toString(), editNote.text.toString())
                )
                finish()
            }
        }


//        Toast.makeText(this, noteId.toString(), Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("RestrictedApi")
    fun setUpView(){
        supportActionBar!!.setDefaultDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType) {
            Constant.TYPE_CREATE -> {
                btnUpdate.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                btnSave.visibility = View.GONE
                btnUpdate.visibility = View.GONE
                getNote()
            }
            Constant.TYPE_UPDATE -> {
                btnSave.visibility = View.GONE
                getNote()
            }
        }
    }

    fun getNote() {
        noteId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val notes = db.listDao().getList(noteId)[0]
            editTitle.setText(notes.title)
            editNote.setText(notes.deskripsi)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}