package com.example.cosc253_hw4_myworkouts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

/*
Hannah Norman
Sophia Petersen
COSC-253 HW4
02/16/2022
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.elevation = 0f   // remove action bar drop shadow
    }//onCreate

    // add exercise to exercise log from each category
    fun add(view: View) {
        val intent = Intent(this, AddActivity::class.java)
        intent.putExtra("Tag", view.tag.toString())
        startActivity(intent)
    } // add

    // switch to exercise log activity
    fun log(view: View) {
        val intent = Intent(this, LogActivity::class.java)
        startActivity(intent)
    } // log

} // MainActivity