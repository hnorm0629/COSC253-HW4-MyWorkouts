package com.example.cosc253_hw4_myworkouts

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class EditActivity : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var note: EditText
    private lateinit var date: EditText
    private var itemIdx = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        title = "View Exercise or Edit Note"

        // populate exercise page with data
        itemIdx = intent.getIntExtra("idx", -1)
        name = findViewById(R.id.addName)
        note = findViewById(R.id.addNote)
        date = findViewById(R.id.addDate)
        name.setText(names[itemIdx])
        note.setText(notes[itemIdx])
        date.setText(dates[itemIdx])

    } // onCreate

    // save data to shared preferences, add entry to exercise log
    fun save(view: View) {
        // send shared preferences to log
        names[itemIdx] = name.text.toString()
        notes[itemIdx] = note.text.toString()
        dates[itemIdx] = date.text.toString()
        arrayAdapter.notifyDataSetChanged()

        sharedPreferences = applicationContext.getSharedPreferences(
            "com.example.cosc253_hw4_myworkouts", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("name", ObjectSerializer.serialize(names)).apply()
        sharedPreferences.edit().putString("note", ObjectSerializer.serialize(notes)).apply()
        sharedPreferences.edit().putString("date", ObjectSerializer.serialize(dates)).apply()

        // TODO: alert dialog - Success! View entry in exercise log. (OK)

        val intent = Intent(this, LogActivity::class.java)
        startActivity(intent)
    } // save

    // return to main menu, save nothing
    fun cancel(view: View) {
        // TODO alert dialog - Are you sure you want to cancel? (Yes/No)

        val intent = Intent(this, LogActivity::class.java)
        startActivity(intent)
    } // cancel

} // EditActivity