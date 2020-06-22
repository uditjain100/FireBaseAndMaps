package udit.programmer.co.firebase.Toolbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_toolbar.*
import udit.programmer.co.firebase.R

class ToolbarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolbar)

        toolbar.setNavigationOnClickListener {
            Toast.makeText(this, "ToolBar Clicked", Toast.LENGTH_LONG).show()
        }
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.refresh -> {
                    Toast.makeText(this, "Refresh Clicked", Toast.LENGTH_LONG).show()
                    true
                }
                else -> false
            }
        }

        activity_btn.setOnClickListener {
            startActivity(
                Intent(
                    this@ToolbarActivity,
                    CollapsingToolbarActivity::class.java
                )
            )
        }

        btm_btn.setOnClickListener {
            startActivity(
                Intent(
                    this@ToolbarActivity,
                    BottomActivity::class.java
                )
            )
        }

        scroll_btn.setOnClickListener {
            startActivity(
                Intent(
                    this@ToolbarActivity,
                    ScrollActivity::class.java
                )
            )
        }

    }
}