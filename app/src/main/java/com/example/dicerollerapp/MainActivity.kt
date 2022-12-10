package com.example.dicerollerapp

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

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
    }

    private fun rollDice() {
        val dice = Dice(6)
        val rollResult = dice.roll()
        val resultTextView: TextView = findViewById(R.id.throwResult)
        resultTextView.text = rollResult.toString()
    }

}


class DiceSpinnerActivity : Activity(), AdapterView.OnItemSelectedListener
{
    override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {

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