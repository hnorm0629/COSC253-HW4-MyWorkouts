package com.example.cosc253_hw4_myworkouts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.elevation = 0f   // remove action bar drop shadow
    }//onCreate

    // change page to each workout category
    fun add(view: View) {
        val intent = Intent(this, AddActivity::class.java)
        intent.putExtra("Tag", view.tag.toString())
        startActivity(intent)
    } // workout

    // change page to each workout category
    fun log(view: View) {
        val intent = Intent(this, LogActivity::class.java)
        startActivity(intent)
    } // workout

} // MainActivity