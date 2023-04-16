//Author: Patrīcija Jukša
//The base for the game taken from this video (watched on 10.04.2023):
//https://www.youtube.com/watch?v=POFvcoRo3Vw
//it has the playing field and two player mode with a message about who won
//the rest of the activities were added by me
//and the information about certain "How tos" were taken from the documentation -
//either Android developer or Kotlin documentation online
package com.example.tictactoejuksa

//import android.widget.TextView
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoejuksa.databinding.ActivityMainBinding
//import com.example.tictactoemegis2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

//all the variables from the video, except countMoves - I added that
    enum class Turn{
        CROSSES,
        NOUGHTS
    }

    private var currentTurn = Turn.CROSSES

    private var countMoves = 0

    private var boardList = mutableListOf<Button>()

    private lateinit var binding : ActivityMainBinding


//from the video, I added setTurnLabel() here so that the first player's name would be shown right away
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    //initalize the board
        initBoard()
    //set the turn label
        setTurnLabel()
    //if the computer starts, make the first move
        val intent = intent
        val game2 = intent.getBooleanExtra("computerStarts", false)
        if(game2){
            val number = (1..9).random()
            var choice: Button = binding.p1
            when(number){
                1 -> choice = binding.p1
                2 -> choice = binding.p2
                3 -> choice = binding.p3
                4 -> choice = binding.p4
                5 -> choice = binding.p5
                6 -> choice = binding.p6
                7 -> choice = binding.p7
                8 -> choice = binding.p8
                9 -> choice = binding.p9
            }
            addToBoard(choice)
        }

    }

//from the video, I added countMoves
    private fun initBoard() {
        boardList.add(binding.p1)
        boardList.add(binding.p2)
        boardList.add(binding.p3)
        boardList.add(binding.p4)
        boardList.add(binding.p5)
        boardList.add(binding.p6)
        boardList.add(binding.p7)
        boardList.add(binding.p8)
        boardList.add(binding.p9)
        countMoves = 1
    }

    //game type, player's names and computer's moves are my adjustments, the rest is from the video
    fun boardTapped(view: View){

        val intent = intent
        val game1 = intent.getBooleanExtra("twoPlayerChosen", false)
        val game2 = intent.getBooleanExtra("computerStarts", false)
        val game3 = intent.getBooleanExtra("playerStarts", false)
        var name1 = ""
        var name2 = ""
        if(game1){
            name1 = intent.getStringExtra("name1").toString()
            name2 = intent.getStringExtra("name2").toString()
        }
        if(game2){
            name1 = "Computer"
            name2 = intent.getStringExtra("name1").toString()
        }
        if(game3){
            name1 = intent.getStringExtra("name1").toString()
            name2 = "Computer"
        }

        if(view !is Button)
            return
        //if a two player game
        if(game1){
            addToBoard(view)
        }
        //computer starts (made the first move right away)
        if(game2){
            //player's move
            addToBoard(view)
            //check for victory - from the video
            if(checkForVictory(NOUGHTS)){
                result("$name2 won!")
            }
            else if(checkForVictory(CROSSES)){
                result("$name1 won!")
            }
            //computer's move if it can be made - player did not win in the previous move
            if(countMoves <= 9 && !checkForVictory(NOUGHTS)){
                while(true){
                    val number = (1..9).random()
                    var choice: Button = binding.p1
                    when(number){
                        1 -> choice = binding.p1
                        2 -> choice = binding.p2
                        3 -> choice = binding.p3
                        4 -> choice = binding.p4
                        5 -> choice = binding.p5
                        6 -> choice = binding.p6
                        7 -> choice = binding.p7
                        8 -> choice = binding.p8
                        9 -> choice = binding.p9
                    }
                    //if successful computer's move
                    if(addToBoard(choice)){
                        break
                    }
                }
            }
        }
        //player starts
        if(game3){
            //if(countMoves%2==0){
            addToBoard(view)
            //check for victory - from the video
            if(checkForVictory(NOUGHTS)){
                result("$name2 won!")
            }
            else if(checkForVictory(CROSSES)){
                result("$name1 won!")
            }

            //computer's move if it can be made - player did not win in the previous move
            if(countMoves < 9 && !checkForVictory(CROSSES)){
                while(true){
                    //chooses a random number
                    val number = (1..9).random()
                    var choice: Button = binding.p1
                    when(number){
                        1 -> choice = binding.p1
                        2 -> choice = binding.p2
                        3 -> choice = binding.p3
                        4 -> choice = binding.p4
                        5 -> choice = binding.p5
                        6 -> choice = binding.p6
                        7 -> choice = binding.p7
                        8 -> choice = binding.p8
                        9 -> choice = binding.p9
                    }
                    //if computer's move was successful
                    if(addToBoard(choice)){
                        break
                    }
                }
            }
        }
        //added this, otherwise if the player wins in the last move
        //the result of the game is draw for some reason
        var win1 = false
        var win2 = false
//check for victory - from the video
        if(checkForVictory(NOUGHTS)){
            result("$name2 won!")
            win1 = true
        }
        else if(checkForVictory(CROSSES)){
            result("$name1 won!")
            win2 = true
        }
//check for draw - from the video
        if(fullBoard() && !win1 && !win2){
            result("Draw")
        }

    }
//check for victory - from the video
    private fun checkForVictory(s: String): Boolean {
        //horizontal
        if(match(binding.p1, s) && match(binding.p2, s) && match(binding.p3, s))
            return true
        if(match(binding.p4, s) && match(binding.p5, s) && match(binding.p6, s))
            return true
        if(match(binding.p7, s) && match(binding.p8, s) && match(binding.p9, s))
            return true
        //vertical
        if(match(binding.p1, s) && match(binding.p4, s) && match(binding.p7, s))
            return true
        if(match(binding.p2, s) && match(binding.p5, s) && match(binding.p8, s))
            return true
        if(match(binding.p3, s) && match(binding.p6, s) && match(binding.p9, s))
            return true
        //diagonal
        if(match(binding.p1, s) && match(binding.p5, s) && match(binding.p9, s))
            return true
        if(match(binding.p3, s) && match(binding.p5, s) && match(binding.p7, s))
            return true
        return false
    }

//check if the button has a certain symbol - from the video
    private fun match(button: Button, symbol: String): Boolean = button.text == symbol
//show the result of the game - from the video, with adjustments - I removed the count
    //of how many times a crosses or noughts have won
    private fun result(title: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setPositiveButton("New Game")
            {_,_ ->
                val intent = Intent(this@MainActivity, SpelesVeidaIzvele::class.java)
                this@MainActivity.startActivity(intent)
            }
            .setCancelable(false)
            .show()
    }


//check for draw - from the video
    private fun fullBoard(): Boolean {
        for(button in boardList){
            if(button.text == "")
                return false
        }
        return true
    }
//add the player's choice to the board - from the video, except for countMoves that I added
    //and I also added the return type Boolean
    private fun addToBoard(button: Button): Boolean {
        if(button.text != "")
            return false
        if(currentTurn == Turn.NOUGHTS){
            button.text = NOUGHTS
            currentTurn = Turn.CROSSES
            countMoves++
        }
            else if (currentTurn == Turn.CROSSES){
            button.text = CROSSES
            currentTurn = Turn.NOUGHTS
            countMoves++
        }
        setTurnLabel()
        return true
    }
//set the label - from the video with my adjustments for the player's chosen names
    private fun setTurnLabel() {
    //my adjustments for player's names
        val intent = intent
        val game1 = intent.getBooleanExtra("twoPlayerChosen", false)
        val game2 = intent.getBooleanExtra("computerStarts", false)
        val game3 = intent.getBooleanExtra("playerStarts", false)
        var name1 = ""
        var name2 = ""
        if(game1){
            name1 = intent.getStringExtra("name1").toString()
            name2 = intent.getStringExtra("name2").toString()
        }
        if(game2){
            name1 = "Computer"
            name2 = intent.getStringExtra("name1").toString()
        }
        if(game3){
            name1 = intent.getStringExtra("name1").toString()
            name2 = "Computer"
        }
    //this next part is fully from the video
        var turnText = ""
        if(currentTurn == Turn.CROSSES)
            turnText = "$name1's turn"
        else if (currentTurn == Turn.NOUGHTS)
            turnText = "$name2's turn"

        binding.turnTV.text = turnText
    }
//from the video
    companion object{
        const val NOUGHTS = "O"
        const val CROSSES = "X"
    }

}