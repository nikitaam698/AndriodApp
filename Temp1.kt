package com.example.brightfuture3.ui.room1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentActivity
import com.example.brightfuture3.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.*


class Temp1 : AppCompatActivity() {
    var toolbar: Toolbar? = null
    private var mRef = FirebaseDatabase.getInstance().reference
    private var temp1: TextView? = null
    private var hum1: TextView? = null
    private var date1: TextView? = null
    private var time1: TextView? = null
    private var lastQuery: Query = mRef.child("devices-telemetry").child("thermostat").orderByKey().limitToLast(1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temp1)
        temp1 = findViewById(R.id.textViewTemp1_Room1)
        hum1 = findViewById(R.id.textViewHum1_Room1)
        date1 = findViewById(R.id.date1)
        time1 = findViewById(R.id.time1)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val fab =
            findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        lastQuery.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds in dataSnapshot.children) {
                    val time: String = ds.child("Time").value.toString()
                    val date: String = ds.child("Day").value.toString()
                    val temp: String = ds.child("Temperature1").value.toString()
                    val hum: String = ds.child("Humidity1").value.toString()
                    temp1?.text = temp
                    hum1?.text = hum
                    time1?.text = time
                    date1?.text = date
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Handle possible errors.
            }
        })
    }
}
