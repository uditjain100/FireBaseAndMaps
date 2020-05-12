package udit.programmer.co.firebase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {

    var list = arrayListOf("")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movie, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = arguments?.getString("MOVIE")
        if (data == "MARVEL") {
            list = arrayListOf("IronMan", "Avengers")
        } else {
            list = arrayListOf("Joker", "SuperMan")
        }
        val adapter = MovieAdapter(list)
        fragment_lv.adapter = adapter
    }
}