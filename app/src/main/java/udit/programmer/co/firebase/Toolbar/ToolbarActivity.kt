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
                    Toast.makeText(this, "ToolBar Clicked", Toast.LENGTH_LONG).show()
                    true
                }
                else -> false
            }
        }

        activity_btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                startActivity(
                    Intent(
                        this@ToolbarActivity,
                        CollapsingToolbarActivity::class.java
                    )
                )
            }
        })

    }
}