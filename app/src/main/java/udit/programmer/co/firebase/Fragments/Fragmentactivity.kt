package udit.programmer.co.firebase.Fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_fragmentactivity.*
import udit.programmer.co.firebase.R

class Fragmentactivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragmentactivity)

        var bundle = Bundle()
        marvel_btn.setOnClickListener {
            bundle.putString("MOVIE", "MARVEL")
            val fragment = MovieFragment()
            fragment.arguments = bundle
            supportFragmentManager.beginTransaction().replace(
                R.id.main_fragment, fragment
            ).commit()
        }

        dc_btn.setOnClickListener {
            bundle.putString("MOVIE", "DC")
            val fragment = MovieFragment()
            fragment.arguments = bundle
            supportFragmentManager.beginTransaction().replace(
                R.id.main_fragment, fragment
            ).commit()
        }
    }
}
