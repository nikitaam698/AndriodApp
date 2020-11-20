package com.example.brightfuture3.ui.room2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.brightfuture3.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.*

class Temp2 : AppCompatActivity() {
    var toolbar: Toolbar? = null
    private var temp2: TextView? = null
    private var hum2: TextView? = null
    private var date2: TextView? = null
    private var time2: TextView? = null
    private var mRef = FirebaseDatabase.getInstance().reference
    private var lastQuery: Query = mRef.child("devices-telemetry").child("thermostat").orderByKey().limitToLast(1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temp2)
        temp2 = findViewById(R.id.textViewTemp2_Room2)
        hum2 = findViewById(R.id.textViewHum2_Room2)
        date2 = findViewById(R.id.date1)
        time2= findViewById(R.id.time1)
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
                    val temp: String = ds.child("Temperature2").value.toString()
                    val hum: String = ds.child("Humidity2").value.toString()
                    temp2?.text = temp
                    hum2?.text = hum
                    time2?.text = time
                    date2?.text = date
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Handle possible errors.
            }
        })

    }
}
