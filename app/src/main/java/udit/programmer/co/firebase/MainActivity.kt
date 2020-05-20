package udit.programmer.co.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import udit.programmer.co.firebase.Camera.CameraActivity
import udit.programmer.co.firebase.FireBase.User
import udit.programmer.co.firebase.Fragments.Fragmentactivity
import udit.programmer.co.firebase.Maps.MapsActivity
import udit.programmer.co.firebase.Notifications.NotificationActivity
import udit.programmer.co.firebase.RoomDatabase.RoomActivity
import udit.programmer.co.firebase.ViewPager.ViewPagerActivity
import udit.programmer.co.firebase.WorkManager.WorkManagerActivity

class MainActivity : AppCompatActivity() {

    val auth by lazy {
        FirebaseAuth.getInstance()
    }
    val db by lazy {
        FirebaseDatabase.getInstance()
    }
    val users by lazy {
        db.getReference("Users")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        google_map_btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                startActivity(Intent(this@MainActivity, MapsActivity::class.java))
            }
        })

        fragment_btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                startActivity(Intent(this@MainActivity, Fragmentactivity::class.java))
            }
        })

        notification_btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                startActivity(Intent(this@MainActivity, NotificationActivity::class.java))
            }
        })

        view_pager_btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                startActivity(Intent(this@MainActivity, ViewPagerActivity::class.java))
            }
        })

        room_btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                startActivity(Intent(this@MainActivity, RoomActivity::class.java))
            }
        })

        camera_btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                startActivity(Intent(this@MainActivity, CameraActivity::class.java))
            }
        })

        worker_btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                startActivity(Intent(this@MainActivity, WorkManagerActivity::class.java))
            }
        })

        btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                auth.createUserWithEmailAndPassword(
                    email_et.text.toString(),
                    password_et.text.toString()
                )
                    .addOnSuccessListener {
                        var user = User(
                            name_et.text.toString(),
                            email_et.text.toString(),
                            password_et.text.toString(),
                            phone_et.text.toString()
                        )
                        users.child(FirebaseAuth.getInstance().currentUser!!.uid)
                            .setValue(user)
                            .addOnSuccessListener {
                                Snackbar.make(
                                    root_layout,
                                    "Registered Successfully",
                                    Snackbar.LENGTH_LONG
                                ).show()
                            }
                            .addOnFailureListener {
                                Snackbar.make(
                                    root_layout,
                                    "FAILED : " + it.toString(),
                                    Snackbar.LENGTH_LONG
                                ).show()
                            }
                    }
                    .addOnFailureListener {
                        Snackbar.make(
                            root_layout,
                            "FAILED : " + it.toString(),
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
            }

        })

    }
}
