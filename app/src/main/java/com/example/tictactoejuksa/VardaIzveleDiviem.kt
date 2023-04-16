//https://www.geeksforgeeks.org/how-to-send-data-from-one-activity-to-second-activity-in-android/
package com.example.tictactoejuksa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class VardaIzveleDiviem : AppCompatActivity() {

    private lateinit var name1: EditText
    private lateinit var name2: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_varda_izvele_diviem)
    }

    fun startGame(view: View) {
        val twoPlayerChosen = true
        val playerChosen = false
        val computerChosen = false
        name1 = findViewById(R.id.editTextTextPlayer1)
        name2 = findViewById(R.id.editTextTextPlayer2)
        val str1 = name1.text.toString()
        val str2 = name2.text.toString()
        val intent = Intent(this@VardaIzveleDiviem, MainActivity::class.java)
        intent.putExtra("name1", str1)
        intent.putExtra("name2", str2)
        intent.putExtra("twoPlayerChosen", twoPlayerChosen)
        intent.putExtra("playerStarts", playerChosen)
        intent.putExtra("computerStarts", computerChosen)
        this@VardaIzveleDiviem.startActivity(intent)
    }
}