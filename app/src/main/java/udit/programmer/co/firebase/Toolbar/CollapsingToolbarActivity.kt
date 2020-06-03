package udit.programmer.co.firebase.Toolbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_collapsing_toolbar.*
import udit.programmer.co.firebase.R

class CollapsingToolbarActivity : AppCompatActivity() {
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
    }
}