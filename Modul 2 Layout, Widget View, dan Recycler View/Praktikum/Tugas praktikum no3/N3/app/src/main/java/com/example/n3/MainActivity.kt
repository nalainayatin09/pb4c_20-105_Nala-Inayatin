package com.example.n3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buahlist = listOf<buah>(
            buah(
                R.drawable.anggur,
                namebuah = "Anggur",
                descbuah = "Buah Anggur Berwarna Ungu "
            ),
            buah(
                R.drawable.apel,
                namebuah = "Apel",
                descbuah = "Buah Apel Berwarna Merah"
            ),
            buah(
                R.drawable.jeruk,
                namebuah = "Jeruk",
                descbuah = "Buah Jeruk Berwarna Orange"
            ),
            buah(
                R.drawable.manggis,
                namebuah = "Manggis",
                descbuah = "Buah Manggis Berwarna Ungu"
            ),
            buah(
                R.drawable.nanas,
                namebuah = "Nanas",
                descbuah = "Buah Nanas Berwarna Kuning"
            ),
            buah(
                R.drawable.strawberry,
                namebuah = "Strawberry",
                descbuah = "Buah Strawberry Berwarna Merah"
            ),
        )
        val recyclerView = findViewById<RecyclerView>(R.id.rv_buah)
        recyclerView.layoutManager = LinearLayoutManager (this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = buahAdapter(this,buahlist){

        }
    }
}