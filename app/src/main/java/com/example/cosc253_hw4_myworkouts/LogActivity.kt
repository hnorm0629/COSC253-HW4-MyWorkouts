package com.example.cosc253_hw4_myworkouts

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import java.util.ArrayList

/*
Hannah Norman
Sophia Petersen
COSC-253 HW4
02/16/2022
 */

lateinit var sharedPreferences: SharedPreferences
lateinit var arrayAdapter: ArrayAdapter<*>
var names = ArrayList<String>()
var notes = ArrayList<String>()
var dates = ArrayList<String>()
var tags = ArrayList<String>()

class LogActivity : AppCompatActivity() {
    private lateinit var list: ListView
    private var listNames = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)
        title = "Exercise Log"

        // initialize exercise list
        list = findViewById(R.id.list)
        getData()
        listNames = getLabels()
        arrayAdapter = ArrayAdapter(this, R.layout.list_item, listNames)
        list.adapter = arrayAdapter

        // click item in list to view and edit
        list.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, i, l ->
                val intent = Intent(applicationContext, EditActivity::class.java)
                intent.putExtra("idx", i)
                startActivity(intent)
            } // OnItemClickListener
    } // onCreate

    // return to main menu
    fun back(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    } // back

    // set up shared preferences for storing exercises
    fun getData() {
        sharedPreferences = applicationContext.getSharedPreferences(
            "com.example.cosc253_hw4_myworkouts", Context.MODE_PRIVATE
        ) // sharedPreferences

        // deserialize names object
        var newExercises = ObjectSerializer
            .deserialize(
                sharedPreferences.getString("name", ObjectSerializer.serialize(ArrayList<String>()))
            ) as ArrayList<String>
        if (newExercises.size != 0) {
            names = ArrayList(newExercises)
        } // if

        // deserialize notes object
        newExercises = ObjectSerializer
            .deserialize(
                sharedPreferences.getString("note", ObjectSerializer.serialize(ArrayList<String>()))
            ) as ArrayList<String>
        if (newExercises.size != 0) {
            notes = ArrayList(newExercises)
        } // if

        // deserialize dates object
        newExercises = ObjectSerializer
            .deserialize(
                sharedPreferences.getString("date", ObjectSerializer.serialize(ArrayList<String>()))
            ) as ArrayList<String>
        if (newExercises.size != 0) {
            dates = ArrayList(newExercises)
        } // if

        // deserialize tags object
        newExercises = ObjectSerializer
            .deserialize(
                sharedPreferences.getString("tag", ObjectSerializer.serialize(ArrayList<String>()))
            ) as ArrayList<String>
        if (newExercises.size != 0) {
            tags = ArrayList(newExercises)
        } // if

    } // getData

    // create custom labels for exercise log entries
    fun getLabels(): ArrayList<String> {
        val labels = ArrayList<String>()
        for (i in 0..(names.size-1)) {
            val label = "" + dates[i] + " - " + tags[i] + ": " + names[i]
            labels.add(label)
        } // for
        return labels
    } // getLabels

} // LogActivity