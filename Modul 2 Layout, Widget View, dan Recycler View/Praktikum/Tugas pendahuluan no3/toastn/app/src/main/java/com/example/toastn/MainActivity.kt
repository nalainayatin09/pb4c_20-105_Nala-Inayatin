package com.example.toastn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val namaBuah = arrayOf("anggur", "apel", "jeruk", "manggis", "nanas", "strawberry")
        var imgBuah = arrayOf(
            R.drawable.anggur,
            R.drawable.apel,
            R.drawable.jeruk,
            R.drawable.manggis,
            R.drawable.nanas,
            R.drawable.strawberry,
            )

        val buahAdapter = BuahAdapter(namaBuah,imgBuah)

        rv_buah.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = buahAdapter
        }
    }
}