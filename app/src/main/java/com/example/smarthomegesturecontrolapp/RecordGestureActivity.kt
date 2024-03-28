@file:Suppress("DEPRECATION")

package com.example.smarthomegesturecontrolapp

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.VideoView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import android.Manifest
import android.content.SharedPreferences
import android.widget.Toast


class RecordGestureActivity : AppCompatActivity() {
    private val REQUEST_VIDEO_CAPTURE = 10


    private val cameraPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                // Camera permission is granted, do nothing
            } else {
                // Camera permission is not granted, show a Toast message
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show()
            }
        }

    private fun checkPermission(permission: String){
        // Check permission
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            // Request for permission
            cameraPermissionLauncher.launch(permission)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_gesture)
        
        val recordButton: Button = findViewById<Button>(R.id.recordButton)
        val uploadButton: Button = findViewById<Button>(R.id.uploadButton)
        val recordVideoView: VideoView = findViewById<VideoView>(R.id.recordvideoView)

        // Check and request camera permissions at runtime
        checkPermission(Manifest.permission.CAMERA)

        // Set an event listener for the record button
        recordButton.setOnClickListener {
            startVideoCapture()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }


    private fun startVideoCapture() {
        // Create an Intent for video capture
        val takeVideoIntent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        // Check if there is an app that can handle the video capture intent
        if (takeVideoIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE)
        }else{
            Toast.makeText(this, "Unable to open camera", Toast.LENGTH_SHORT).show()
        }

    }


}