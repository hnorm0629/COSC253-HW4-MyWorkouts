package com.example.cosc253_hw4_myworkouts

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

/*
Hannah Norman
Sophia Petersen
COSC-253 HW4
02/16/2022
 */

class AddActivity : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var note: EditText
    private lateinit var date: EditText
    private var tag = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        // get exercise tag
        tag = intent.getStringExtra("Tag").toString()
        title = "New $tag Exercise"

        name = findViewById(R.id.addName)
        note = findViewById(R.id.addNote)
        date = findViewById(R.id.addDate)
    } // onCreate

    // save data to shared preferences, add entry to exercise log
    fun save(view: View) {
        // don't allow exercise entry to save w/o proper name and date
        if (date.length() == 0 && name.length() == 0) {
            Toast.makeText(applicationContext,
                "You must enter a name and date!", Toast.LENGTH_SHORT).show()
            return
        }
        else if (date.length() == 0) {
            Toast.makeText(applicationContext,
                "You must enter a date!", Toast.LENGTH_SHORT).show()
            return
        } else if (name.length() == 0) {
            Toast.makeText(applicationContext,
                "You must enter a name!", Toast.LENGTH_SHORT).show()
            return
        } // if-elif

        if (date.length() != 10) {
            Toast.makeText(applicationContext,
                "Date must be in MM/DD/YYYY format!", Toast.LENGTH_SHORT).show()
            return
        } // if

        // send shared preferences to log
        names.add(name.text.toString())
        notes.add(note.text.toString())
        dates.add(date.text.toString())
        tags.add(tag)

        sharedPreferences = applicationContext.getSharedPreferences(
            "com.example.cosc253_hw4_myworkouts", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("name", ObjectSerializer.serialize(names)).apply()
        sharedPreferences.edit().putString("note", ObjectSerializer.serialize(notes)).apply()
        sharedPreferences.edit().putString("date", ObjectSerializer.serialize(dates)).apply()
        sharedPreferences.edit().putString("tag", ObjectSerializer.serialize(tags)).apply()

        // success alert dialog
        AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle)
            .setIcon(android.R.drawable.ic_dialog_info)
            .setTitle("Success!")
            .setMessage("You can view this exercise in the log.")
            .setNeutralButton("OK", DialogInterface.OnClickListener {
                    dialog, id ->
                val intent = Intent(this, LogActivity::class.java)
                startActivity(intent)
                finish()
            }).show()
    } // save

    // return to main menu, save nothing
    fun cancel(view: View) {
        // cancel alert dialog
        AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle("Are you sure you wish to cancel?")
            .setMessage("Your changes will not be saved.")
            .setPositiveButton("Yes", DialogInterface.OnClickListener {
                    dialog, id ->
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            })
            .setNegativeButton("No", DialogInterface.OnClickListener { dialog, id ->
            }).show()
    } // cancel

} // AddActivity