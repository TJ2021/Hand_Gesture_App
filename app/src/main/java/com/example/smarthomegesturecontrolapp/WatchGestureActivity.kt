package com.example.smarthomegesturecontrolapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.VideoView

class WatchGestureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watch_gesture)

        // Retrieve the selected gesture from the Intent
        val selectedGesture = intent.getStringExtra("selectedGesture")
        val gestureTextView: TextView = findViewById(R.id.selectedGesture)
        gestureTextView.text =  "$selectedGesture"

        val gestureVideo :VideoView = findViewById(R.id.gesturevideoView) as VideoView
        val replayButton: Button = findViewById(R.id.replayButton) as Button
        val practiseButton: Button = findViewById(R.id.practiseButton) as Button

        val gestureToPlay = "h_" + selectedGesture!!.replace(" ", "_").lowercase()

        // Set the path of the video file
        val videoPath = "android.resource://${packageName}/raw/${gestureToPlay}"

        // Set the URI for the video
        val videoUri = Uri.parse(videoPath)
        // Set the video URI to the VideoView
        gestureVideo.setVideoURI(videoUri)
        gestureVideo.start()

        // Set an event listener for the replay  button
        replayButton.setOnClickListener {
            // Play the video again when the practice button is clicked
            gestureVideo.start()
        }
        // Set an event listener for the practise button
        practiseButton.setOnClickListener {
            // Create an Intent to practise the selected gesture
            val intent = Intent(this, RecordGestureActivity::class.java)
            // Start the new activity
            startActivity(intent)

        }




    }
}