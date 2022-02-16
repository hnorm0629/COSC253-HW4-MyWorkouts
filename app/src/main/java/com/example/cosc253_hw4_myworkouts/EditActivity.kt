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

class EditActivity : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var note: EditText
    private lateinit var date: EditText
    private var itemIdx = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        title = "View Exercise"

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

    // return to log, save nothing
    fun cancel(view: View) {
        // cancel alert dialog
        AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle("Are you sure you wish to cancel?")
            .setMessage("Your changes will not be saved.")
            .setPositiveButton("Yes", DialogInterface.OnClickListener {
                    dialog, id ->
                val intent = Intent(this, LogActivity::class.java)
                startActivity(intent)
                finish()
            })
            .setNegativeButton("No", DialogInterface.OnClickListener { dialog, id ->
            }).show()
    } // cancel

    // display toast alerts if user attempts to edit name or date
    fun toast(view: View) {
        if (view.tag == "addName") {
            Toast.makeText(applicationContext, "Cannot edit name!", Toast.LENGTH_SHORT).show()
        } else if (view.tag == "addDate") {
            Toast.makeText(applicationContext, "Cannot edit date!", Toast.LENGTH_SHORT).show()
        } // if-elif
    } // toast

} // EditActivity