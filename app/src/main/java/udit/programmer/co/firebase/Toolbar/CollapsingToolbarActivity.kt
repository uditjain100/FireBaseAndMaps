package udit.programmer.co.firebase.Toolbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_collapsing_toolbar.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import udit.programmer.co.firebase.R

class CollapsingToolbarActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collapsing_toolbar)

        c_toolbar.setNavigationOnClickListener {
            Toast.makeText(this, "ToolBar Clicked", Toast.LENGTH_LONG).show()
        }
        c_toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.refresh -> {
                    Toast.makeText(this, "Refresh Clicked", Toast.LENGTH_LONG).show()
                    true
                }
                else -> false
            }
        }

        val nav_controller = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_home), drawer_layout)
        nav_view.setupWithNavController(nav_controller)

    }
}