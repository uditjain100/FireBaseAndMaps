package udit.programmer.co.firebase.Toolbar

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trendyol.bubblescrollbarlib.BubbleTextProvider
import kotlinx.android.synthetic.main.activity_scroll.*
import udit.programmer.co.firebase.Adapter.MovieAdapter
import udit.programmer.co.firebase.Adapter.MovieOnItemClickListener
import udit.programmer.co.firebase.Adapter.Movies
import udit.programmer.co.firebase.R

class ScrollActivity : AppCompatActivity() {

    private val moviesn = arrayOf(
        "Superman and the Mole Men",
        "Stamp Day for Superman",
        "Batman",
        "Superman",
        "Superman II",
        "Swamp Thing",
        "SuperMan3",
        "SuperGirl",
        "Superman 4",
        "The Return Of Swamp Things",
        "Batman",
        "Batman Returns",
        "Batman Foreever",
        "Batman & Robin",
        "Steel",
        "CatWomen",
        "Constantine",
        "Batman Begins",
        "Superman Returns",
        "The Dark Knight",
        "WatchMan",
        "jonah Hex",
        "Green Lantern",
        "The Dark Knight Rises",
        "Man Of Steel",
        "Batman v Superman: Dawn of Justice",
        "Suicide Squad",
        "Wonder Women",
        "Justice League",
        "Aquaman",
        "Shazam",
        "Joker"
    )
    private val years = arrayOf(
        "1951", "1954", "1966", "1978",
        "1980", "1982", "1983", "1984",
        "1987", "1989", "1989", "1992",
        "1995", "1997", "1997", "2004",
        "2005", "2005", "2006", "2008",
        "2009", "2010", "2011", "2012",
        "2013", "2016", "2016", "2017",
        "2017", "2018", "2019", "2019"
    )
    private val images = arrayOf(
        R.drawable.supermanmolemen,
        R.drawable.stampday,
        R.drawable.batman,
        R.drawable.superman,
        R.drawable.superman2,
        R.drawable.swampthing,
        R.drawable.superman3,
        R.drawable.supergirl,
        R.drawable.superman4,
        R.drawable.returnofswampthing,
        R.drawable.batman1989,
        R.drawable.batmanreturns,
        R.drawable.batmanforever,
        R.drawable.batmanandfore,
        R.drawable.steel,
        R.drawable.catwomen,
        R.drawable.constatine,
        R.drawable.batmanbegins,
        R.drawable.supermanreturns,
        R.drawable.thedarkknight,
        R.drawable.watchman,
        R.drawable.johanhex,
        R.drawable.greenlantern,
        R.drawable.thedarkknightrises,
        R.drawable.manofsteel,
        R.drawable.batmanvssuperman,
        R.drawable.suicide,
        R.drawable.wonderwoman,
        R.drawable.justiceleague,
        R.drawable.aquaman,
        R.drawable.shazam,
        R.drawable.jokar
    )
    private val links = arrayOf(
        "en.wikipedia.org/wiki/Superman_and_the_Mole_Men",
        "en.wikipedia.org/wiki/Stamp_Day_for_Superman",
        "en.wikipedia.org/wiki/Batman_(1966_film)",
        "en.wikipedia.org/wiki/Superman_(1978_film)",
        "en.wikipedia.org/wiki/Superman_II",
        "en.wikipedia.org/wiki/Swamp_Thing_(film)",
        "en.wikipedia.org/wiki/Superman_III",
        "en.wikipedia.org/wiki/Supergirl_(1984_film)",
        "en.wikipedia.org/wiki/Superman_IV:_The_Quest_for_Peace",
        "en.wikipedia.org/wiki/The_Return_of_Swamp_Thing",
        "en.wikipedia.org/wiki/Batman_(1989_film)",
        "en.wikipedia.org/wiki/Batman_Returns",
        "en.wikipedia.org/wiki/Batman_Forever",
        "en.wikipedia.org/wiki/Batman_%26_Robin_(film)",
        "en.wikipedia.org/wiki/Steel_(1997_film)",
        "en.wikipedia.org/wiki/Catwoman_(film)",
        "en.wikipedia.org/wiki/Constantine_(film)",
        "en.wikipedia.org/wiki/Batman_Begins",
        "en.wikipedia.org/wiki/Superman_Returns",
        "en.wikipedia.org/wiki/The_Dark_Knight_(film)",
        "en.wikipedia.org/wiki/Watchmen_(film)",
        "en.wikipedia.org/wiki/Jonah_Hex_(film)",
        "en.wikipedia.org/wiki/Green_Lantern_(film)",
        "en.wikipedia.org/wiki/The_Dark_Knight_Rises",
        "en.wikipedia.org/wiki/Man_of_Steel_(film)",
        "en.wikipedia.org/wiki/Batman_v_Superman:_Dawn_of_Justice",
        "en.wikipedia.org/wiki/Suicide_Squad_(film)",
        "en.wikipedia.org/wiki/Wonder_Woman_(2017_film)",
        "en.wikipedia.org/wiki/Justice_League_(film)",
        "en.wikipedia.org/wiki/Aquaman_(film)",
        "en.wikipedia.org/wiki/Shazam!_(film)",
        "en.wikipedia.org/wiki/Joker_(2019_film)"

    )
    private val posit = arrayOf(
        0, 1, 2, 3,
        4, 5, 6, 7,
        8, 9, 10, 11,
        12, 13, 14, 15,
        16, 17, 18, 19,
        20, 21, 22, 23,
        24, 25, 26, 27,
        28, 29, 30, 31
    )

    val list: ArrayList<Movies> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll)

        for (i in 0..31) {
            list.add(Movies(moviesn[i], years[i], images[i], links[i], posit[i]))
        }

        rv_lay.setHasFixedSize(true)
        rv_lay.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val na = MovieAdapter(list)
        na.onItemClickListener = object : MovieOnItemClickListener {
            override fun onClick(movie: Movies) {
                val i = Intent(Intent.ACTION_VIEW, Uri.parse("http://${movie.link}"))
                startActivity(Intent.createChooser(i, "Select Browser"))
            }
        }
        rv_lay.adapter = na

        bubble_scroll_bar.attachToRecyclerView(rv_lay)
        bubble_scroll_bar.bubbleTextProvider = BubbleTextProvider {
            posit[it].toString()
        }

    }
}