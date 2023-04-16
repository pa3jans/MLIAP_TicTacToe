package com.example.tictactoejuksa

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class SpelesVeidaIzvele : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speles_veida_izvele)
    }


//if two player game was chosen
    fun chooseName2p(view: View) {
        val intent = Intent(this@SpelesVeidaIzvele, VardaIzveleDiviem::class.java)
        this@SpelesVeidaIzvele.startActivity(intent)

    }
//if one player game was chosen where the computer starts
    fun gameComputer(view: View) {
        val compChosen = true
        val twoPlayerChosen = false
        val playerChosen = false
        val intent = Intent(this@SpelesVeidaIzvele, VardaIzveleVienam::class.java)
        intent.putExtra("computerStarts", compChosen)
        intent.putExtra("twoPlayerChosen", twoPlayerChosen)
        intent.putExtra("playerStarts", playerChosen)
        this@SpelesVeidaIzvele.startActivity(intent)

    }
//if one player game was chosen where the player starts
    fun gamePlayer(view: View) {
    val compChosen = false
    val twoPlayerChosen = false
    val playerChosen = true
    val intent = Intent(this@SpelesVeidaIzvele, VardaIzveleVienam::class.java)
    intent.putExtra("computerStarts", compChosen)
    intent.putExtra("twoPlayerChosen", twoPlayerChosen)
    intent.putExtra("playerStarts", playerChosen)
    this@SpelesVeidaIzvele.startActivity(intent)
    }
}