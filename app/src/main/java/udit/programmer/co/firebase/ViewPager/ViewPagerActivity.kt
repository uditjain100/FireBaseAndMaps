package udit.programmer.co.firebase.ViewPager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_view_pager.*
import udit.programmer.co.firebase.Fragments.MovieFragment
import udit.programmer.co.firebase.R

class ViewPagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        val bundle = Bundle()
        bundle.putString("MOVIE", "MARVEL")
        var fragment = MovieFragment()
        fragment.arguments = bundle

        var viewPagerAdapter =
            ViewPageAdapter(supportFragmentManager)
        viewPagerAdapter.apply {
            add(fragment)
            //add(fragment)
            //add(fragment)
        }
        view_pager.adapter = viewPagerAdapter

    }
}
