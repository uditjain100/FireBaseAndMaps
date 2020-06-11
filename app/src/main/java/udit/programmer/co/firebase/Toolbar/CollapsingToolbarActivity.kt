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
import kotlinx.android.synthetic.main.activity_view_pager.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import udit.programmer.co.firebase.R

class CollapsingToolbarActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    var fragment_list: MutableList<QuestionFragment> = mutableListOf()
    lateinit var fragment_adapter: CollapsingFragmentAdapter

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
                    list_dispaly()
                    true
                }
                else -> false
            }
        }

        val nav_controller = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_home), drawer_layout)
        nav_view.setupWithNavController(nav_controller)

    }

    private fun list_dispaly() {
        generateFragmentList(25)

        fragment_adapter = CollapsingFragmentAdapter(supportFragmentManager, fragment_list)
        coll_view_pager.offscreenPageLimit = fragment_list.size
        coll_view_pager.adapter = fragment_adapter

        scrolling_tabs.setupWithViewPager(coll_view_pager)
    }

    private fun generateFragmentList(size: Int) {
        for (i in 0..size) {
            var bundle = Bundle()
            bundle.putInt("index", i)
            val fragment = QuestionFragment()
            fragment.arguments = bundle
            fragment_list.add(fragment)
        }
    }

}