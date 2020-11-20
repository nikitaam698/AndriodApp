ppackage com.example.brightfuture3.ui.room1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.brightfuture3.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.*

class Pir1 : AppCompatActivity() {
    var toolbar: Toolbar? = null
    private var pir1: TextView? = null
    private var date1: TextView? = null
    private var time1: TextView? = null
    private var mRef = FirebaseDatabase.getInstance().reference
    private var lastQuery: Query = mRef.child("devices-telemetry").child("thermostat").orderByKey().limitToLast(1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pir1)
        pir1 = findViewById(R.id.textViewPir1_Room1)
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
                    val pir: String = ds.child("Room1").value.toString()
                    if (pir=="0"){
                        val toast = Toast.makeText(applicationContext, "No one is currently in the room right now", Toast.LENGTH_LONG)
                        toast.show()
                        toast.setGravity(Gravity.TOP or Gravity.CENTER, 0, 150)

                    }
                    else if (pir=="1"){
                        val toast= Toast.makeText(applicationContext,"There is someone in the room", Toast.LENGTH_LONG)
                        toast.show()
                        toast.setGravity(Gravity.TOP or Gravity.CENTER, 0, 150)
                    }
                    pir1?.text = pir
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
