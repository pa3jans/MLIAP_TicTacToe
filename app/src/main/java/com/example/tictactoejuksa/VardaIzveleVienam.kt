package com.example.tictactoejuksa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class VardaIzveleVienam : AppCompatActivity() {

    private lateinit var name1: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_varda_izvele_vienam)
    }

    fun startGame(view: View) {
        name1 = findViewById(R.id.editTextTextPlayer)
        val intent1 = intent
        val twoPlayer = intent1.getBooleanExtra("twoPlayerChosen", false)
        val computerChosen = intent1.getBooleanExtra("computerStarts", false)
        val playerChosen = intent1.getBooleanExtra("playerStarts", false)
        val str1 = name1.text.toString()
        val intent = Intent(this@VardaIzveleVienam, MainActivity::class.java)
        intent.putExtra("name1", str1)
        intent.putExtra("twoPlayerChosen", twoPlayer)
        intent.putExtra("computerStarts", computerChosen)
        intent.putExtra("playerStarts", playerChosen)
        this@VardaIzveleVienam.startActivity(intent)
    }
}