package udit.programmer.co.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import udit.programmer.co.firebase.FireBase.AuthenticatedActivity
import udit.programmer.co.firebase.FireBase.FireBaseActivity
import udit.programmer.co.firebase.FireBase.SignInActivity
import udit.programmer.co.firebase.Fragments.Fragmentactivity
import udit.programmer.co.firebase.Layout.LayoutActivity
import udit.programmer.co.firebase.MVVM.ui.view.MVVM_Activity
import udit.programmer.co.firebase.Maps.MapsActivity
import udit.programmer.co.firebase.Notifications.NotificationActivity
import udit.programmer.co.firebase.RoomDatabase.RoomActivity
import udit.programmer.co.firebase.Search.SearchActivity
import udit.programmer.co.firebase.Sensors.SensorsActivity
import udit.programmer.co.firebase.Toolbar.ToolbarActivity
import udit.programmer.co.firebase.ViewPager.ViewPagerActivity
import udit.programmer.co.firebase.WorkManager.WorkManagerActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sign_in_btn.setOnClickListener {
            startActivity(Intent(this@MainActivity, SignInActivity::class.java))
        }

        phone_btn.setOnClickListener {
            startActivity(Intent(this@MainActivity, AuthenticatedActivity::class.java))
        }

        search_btn.setOnClickListener {
            startActivity(Intent(this@MainActivity, SearchActivity::class.java))
        }

        back_btn.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    LayoutActivity::class.java
                )
            )
        }

        toolbar_btn.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    ToolbarActivity::class.java
                )
            )
        }

        sensor_btn.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    SensorsActivity::class.java
                )
            )
        }

        google_map_btn.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    MapsActivity::class.java
                )
            )
        }

        fragment_btn.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    Fragmentactivity::class.java
                )
            )
        }

        notification_btn.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    NotificationActivity::class.java
                )
            )
        }

        view_pager_btn.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    ViewPagerActivity::class.java
                )
            )
        }

        room_btn.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    RoomActivity::class.java
                )
            )
        }

        mvvm_btn.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    MVVM_Activity::class.java
                )
            )
        }

        worker_btn.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    WorkManagerActivity::class.java
                )
            )
        }

        firebase_btn.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    FireBaseActivity::class.java
                )
            )
        }

    }
}
