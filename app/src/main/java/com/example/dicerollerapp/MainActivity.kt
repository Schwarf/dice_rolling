package com.example.dicerollerapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity(), OnItemSelectedListener {

    @SuppressLint("MissingInflatedId")
    private var dice: Dice = Dice()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollButton: Button = findViewById(R.id.rollButton)
        val diceSpinner: Spinner = findViewById(R.id.diceSpinner)
        val numberOfDice: EditText = findViewById(R.id.numberOfDiceInput)

        ArrayAdapter.createFromResource(
            this, R.array.choice_of_dice,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            diceSpinner.adapter = adapter
        }
        diceSpinner.onItemSelectedListener = this
        rollButton.setOnClickListener {
            Toast.makeText(this, "D${dice.numberOfFaces} rolled!", Toast.LENGTH_SHORT).show()
            val dices: Int = Integer.parseInt(numberOfDice.text.toString())
            rollManyDice(dices)
            //rollSingleDie()
        }

    }

    private fun rollManyDice(numberOfDice: Int) {
        val diceRolls = IntArray(numberOfDice)
        for(i in diceRolls.indices)
            diceRolls[i] += dice.roll().toInt()
        val resultTextView: TextView = findViewById(R.id.throwResult)
        resultTextView.text = diceRolls.joinToString()
        //resultTextView.text ="2";
    }

    private fun rollSingleDie() {
        val rollResult = dice.roll()
        val resultTextView: TextView = findViewById(R.id.throwResult)
        resultTextView.text = rollResult.toString()
    }

    override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val diceMaximumValue: Long = id + 1;
        dice.numberOfFaces = diceMaximumValue
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

}


class Dice() {
    var numberOfFaces: Long = 0
    fun roll(): Long {
        return (1..numberOfFaces).random()
    }
}