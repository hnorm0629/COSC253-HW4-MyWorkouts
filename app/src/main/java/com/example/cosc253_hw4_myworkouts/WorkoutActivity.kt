package com.example.cosc253_hw4_myworkouts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class WorkoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // retrieve correct xml activity to move to next page
        val tag = intent.getStringExtra("Tag").toString()
        var layout = 0
        when (tag){
            "core" -> {
                layout = R.layout.activity_core
            } // core
            "weights" -> {
                layout = R.layout.activity_weights
            } // weights
            "yoga" -> {
                layout = R.layout.activity_yoga
            } // yoga
            "stretching" -> {
                layout = R.layout.activity_stretching
            } // stretching
            "bike" -> {
                layout = R.layout.activity_bike
            } // bike
            "cardio" -> {
                layout = R.layout.activity_cardio
            } // cardio
            else -> println("Should not arrive here.")
        } // when
        setContentView(layout)
    } // onCreate

    //go back to workout selection page from individual workout list
    fun back(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    } // back
}