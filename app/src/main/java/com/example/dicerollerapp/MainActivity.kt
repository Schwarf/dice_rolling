package com.example.dicerollerapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), OnItemSelectedListener {

    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollButton: Button = findViewById(R.id.rollButton)
        val diceSpinner: Spinner = findViewById(R.id.diceSpinner)
        ArrayAdapter.createFromResource(this,R.array.choice_of_dice,
        android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            diceSpinner.adapter = adapter
        }
        rollButton.setOnClickListener {
            Toast.makeText(this, "Dice Rolled!", Toast.LENGTH_SHORT).show()
            rollDice()
        }
        diceSpinner.onItemSelectedListener = this
    }

    private fun rollDice() {
        val dice = Dice(6)
        val rollResult = dice.roll()
        val resultTextView: TextView = findViewById(R.id.throwResult)
        resultTextView.text = rollResult.toString()
    }

    override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
        println("position = $position")
        println("id = $id")
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}



class Dice(private val numberOfFaces: Int) {
    fun roll(): Int {
        return (1..numberOfFaces).random()
    }
}