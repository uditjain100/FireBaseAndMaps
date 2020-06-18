package udit.programmer.co.firebase.Search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.work.impl.Schedulers
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mancj.materialsearchbar.MaterialSearchBar
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.disposables.ArrayCompositeDisposable
import kotlinx.android.synthetic.main.activity_search.*
import org.intellij.lang.annotations.Language
import org.json.JSONArray
import udit.programmer.co.firebase.R

class SearchActivity : AppCompatActivity() {

    var compositeDisposable = CompositeDisposable()
    var suggestions: MutableList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        search_bar.addTextChangeListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                getSuggestions(
                    s.toString(), "chrome", "en",
                    if (rdi_youtube.isChecked) "yt" else ""
                )
            }

        })
        search_bar.setOnSearchActionListener(object : MaterialSearchBar.OnSearchActionListener {
            override fun onButtonClicked(buttonCode: Int) {

            }

            override fun onSearchStateChanged(enabled: Boolean) {

            }

            override fun onSearchConfirmed(text: CharSequence?) {
                Log.e("Ceased 03 ", text.toString())
                Toast.makeText(this@SearchActivity, "Ceased Meteor 03 $text", Toast.LENGTH_LONG)
                    .show()
            }
        })
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    private fun getSuggestions(query: String, client: String, language: String, restrict: String) {
        if (TextUtils.isEmpty(restrict)) {
            compositeDisposable.add(
                Client.api.getSuggestFromGoogle(query, client, language)
                    .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        if (suggestions.size > 0) suggestions.clear()
                        val jsonArray = JSONArray(it).getString(1)
                        suggestions = Gson().fromJson<List<String>>(
                            jsonArray,
                            object : TypeToken<List<String>>() {}.type
                        ).toMutableList()
                        search_bar.updateLastSuggestions(suggestions)
                    },
                        { throwable ->
                            Log.e("Ceased 00 ", throwable.message)
                            Toast.makeText(
                                this,
                                "Ceased Meteor 00 ${throwable.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        })
            )
        } else {
            compositeDisposable.add(
                Client.api.getSuggestFromYoutube(query, client, language, restrict)
                    .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        if (suggestions!!.size > 0) suggestions!!.clear()
                        val jsonArray = JSONArray(it).getString(1)
                        suggestions = Gson().fromJson<List<String>>(
                            jsonArray,
                            object : TypeToken<List<String>>() {}.type
                        ).toMutableList()
                        search_bar.updateLastSuggestions(suggestions)
                    },
                        { throwable ->
                            Log.e("Ceased 01", throwable.message)
                            Toast.makeText(
                                this,
                                "Ceased Meteor 01 ${throwable.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        })
            )
        }
    }
}