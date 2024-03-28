package com.example.smarthomegesturecontrolapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Get the spinner reference from the layout
        // The result of findViewById is a general View type
        val gestureSpinner: Spinner = findViewById(R.id.gesture ) as Spinner

        // array of items for the spinner
        val gestureOptions = arrayOf(
            "Please Select",
            "Turn on lights",
            "Turn off lights",
            "Turn on fan",
            "Turn off fan",
            "Increase fan speed",
            "decrease fan speed",
            "Set Thermostat to specified temperature",
            "0",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9"
        )

        // An ArrayAdapter is created to populate the Spinner with data.
        // android.R.layout.simple_spinner_item -> Specifies layout of individual items in the spinner
        gestureSpinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gestureOptions)

        val confirmButton: Button = findViewById(R.id.confirm) as Button
        confirmButton.setOnClickListener {
           val selectedGesture = gestureSpinner.selectedItem.toString()
            if (selectedGesture =="Please Select"){
                Toast.makeText(this, "Please select a gesture", Toast.LENGTH_SHORT).show()
            }else{
                // Create an Intent to watch the selected gesture
                val intent = Intent(this, WatchGestureActivity::class.java)
                // pass selected gesture to screen 2
                intent.putExtra("selectedGesture", selectedGesture)
                // Start the new activity
                startActivity(intent)
            }

        }

    }

}