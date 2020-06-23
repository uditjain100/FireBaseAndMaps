package udit.programmer.co.firebase.JetPack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_data_binding.*
import udit.programmer.co.firebase.R
import udit.programmer.co.firebase.databinding.ActivityMainBinding

class DataBindingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

//        View Binding Work is more safer in case of Null Pointer Exceptions
//        Such as in case of changing Orientations , it can handle it easily

//        Below 2 lines code is used to add view binding features in this activity

//        val binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        setContentView(R.layout.activity_data_binding)
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(MainActivityObserver())

        tvv.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Toast.makeText(this@DataBindingActivity, "$s", Toast.LENGTH_SHORT).show()
                text_view.setText(s.toString())
            }
        })

    }
}