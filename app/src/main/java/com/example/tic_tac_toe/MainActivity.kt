package com.example.tic_tac_toe

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlin.math.log

class MainActivity : AppCompatActivity(),View.OnClickListener {

    var playerOne: TextView ??= null
    var playerTwo: TextView ??= null
    var playerOneScore: TextView ??= null
    var playerTwoScore: TextView ??= null

    var winStatus: TextView ??= null
    var resetBtn:Button ??= null
    var buttons = arrayOfNulls<Button>(9)

    var currentPlayer:Boolean = true

    var p1ScoreCount:Int = 0
    var p2ScoreCount:Int = 0
    var rowCount:Int = 0

    var gameState = mutableListOf(2,2,2,2,2,2,2,2,2)

    var winPos = mutableListOf(
        //Rows
        mutableListOf(0, 1, 2),
        mutableListOf(3, 4, 5),
        mutableListOf(6, 7, 8),
        //Columns
        mutableListOf(0, 3, 6),
        mutableListOf(1, 4, 7),
        mutableListOf(2, 5, 8),
        //Diagonals
        mutableListOf(1, 4, 8),
        mutableListOf(2 ,4, 6)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playerOneScore = findViewById(R.id.score_one)
        playerTwoScore = findViewById(R.id.score_two)
        playerOne = findViewById(R.id.player_one)
        playerTwo = findViewById(R.id.player_two)
        resetBtn = findViewById(R.id.reset_button)
        winStatus = findViewById(R.id.winStatus)

        for (i in buttons.indices) {
            val btnId = "button$i"
            val resID = resources.getIdentifier(btnId,"id",packageName)
            buttons[i] = findViewById(resID)
            buttons[i]!!.setOnClickListener(this)
            print(buttons[i])
        }

        currentPlayer = true

        resetBtn!!.setOnClickListener(View.OnClickListener {
            playAgain()
        })
    }
    override fun onClick(v: View) {
        if ((v as Button).text.toString() != "") {
            return
        }
        val buttonId:String = v.resources.getResourceEntryName(v.id)
        val gameStatePoint:Int = buttonId.substring(buttonId.length-1,buttonId.length).toInt()
        Log.i("test","$gameStatePoint")

        if(currentPlayer){
            (v.setText("X"))
            (v.setTextColor(Color.parseColor("#FFCC80")))

            gameState[gameStatePoint] = 0
        }else
        {
            (v.setText("O"))
            (v.setTextColor(Color.parseColor("#90CAF9")))
            gameState[gameStatePoint] = 0
        }
        rowCount++

        if(checkWinner()){
            if(currentPlayer){
                p1ScoreCount++
                updateScore()
                Toast.makeText(this,"Player One Won!",Toast.LENGTH_LONG).show()
                playAgain()
            }else{
                p2ScoreCount++
                updateScore()
                Toast.makeText(this,"Player Two Won!",Toast.LENGTH_LONG).show()
                playAgain()
            }
        }else if(rowCount==9){
            playAgain()
            Toast.makeText(this,"No Winner!",Toast.LENGTH_LONG).show()
        }else{
            currentPlayer = !currentPlayer
        }

    }
    fun checkWinner() : Boolean{
        var winResult = false
        if(gameState[winPos[0][0]]==gameState[winPos[1][1]]&&
                gameState[winPos[1][1]]==gameState[winPos[2][2]]&&
                    gameState[winPos[0][0]]!=2){
            winResult = true
        }
        return winResult
    }
    fun updateScore() {
        playerOneScore!!.setText(Integer.toString(p1ScoreCount))
        playerTwoScore!!.setText(Integer.toString(p2ScoreCount))
    }
    fun playAgain(){
        rowCount = 0
        currentPlayer = true

        for (i in buttons.indices) {
            gameState[i] = 2
            buttons[i]!!.text = ""
        }
    }
}