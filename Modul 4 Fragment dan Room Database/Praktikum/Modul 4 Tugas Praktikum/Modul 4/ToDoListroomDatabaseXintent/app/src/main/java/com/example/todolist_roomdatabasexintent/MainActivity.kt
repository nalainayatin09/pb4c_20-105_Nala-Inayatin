package com.example.todolist_roomdatabasexintent

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.todolist_roomdatabasexintent.room.Constant
import com.example.todolist_roomdatabasexintent.room.Note
import com.example.todolist_roomdatabasexintent.room.NoteDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var btnCreate : Button
//    private lateinit var editTitle : EditText
    private lateinit var listNote : RecyclerView

    val db by lazy { NoteDb(this) }
    lateinit var listAdapter: ListAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        editTitle = findViewById(R.id.edit_title)
        listNote = findViewById(R.id.list_note)
        btnCreate = findViewById(R.id.button_create)


        btnCreate.setOnClickListener {
//            startActivity(Intent(this, EditActivity::class.java))
            intentEdit(0, Constant.TYPE_CREATE)
        }
        setUpRecyclerView()

    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    fun loadData(){
        CoroutineScope(Dispatchers.IO).launch {
            val notes = db.listDao().getLists()
            Log.d("MainActivity", "dbResponse: $notes")
            withContext(Dispatchers.Main){
                listAdapter.setData( notes )
            }
        }
    }

    fun intentEdit(noteId: Int, intentType: Int){
        startActivity(
            Intent(applicationContext, EditActivity::class.java)
                .putExtra("intent_id",noteId)
                .putExtra("intent_type",intentType)
        )
    }

    private fun setUpRecyclerView() {
        listAdapter = ListAdapter(arrayListOf(), object : ListAdapter.OnAdapterListener{
            override fun onClick(note: Note) {
//                Toast.makeText(applicationContext, note.title, Toast.LENGTH_SHORT).show()
                intentEdit(note.id, Constant.TYPE_READ)
            }

            override fun onUpdate(note: Note) {
                intentEdit(note.id, Constant.TYPE_UPDATE)
            }

            override fun onDelete(note: Note) {
                deleteDialog(note)
            }

        })
        listNote.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = listAdapter
        }
    }

    private fun deleteDialog(note: Note) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Konfrimasi")
            setMessage("yakin menghapus ToDolist dari ${note.title}?")
            setNegativeButton("Batal") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Hapus") { dialogInterface, i ->
                dialogInterface.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    db.listDao().deleteList(note)
                    loadData()
                }
            }
        }
        alertDialog.show()
    }
}