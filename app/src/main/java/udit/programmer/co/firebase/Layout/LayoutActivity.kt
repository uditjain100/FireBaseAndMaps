package udit.programmer.co.firebase.Layout

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import kotlinx.android.synthetic.main.back_layout.*
import kotlinx.android.synthetic.main.front_layout.*
import udit.programmer.co.firebase.R

class LayoutActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)

        rdi_picture_one.setOnCheckedChangeListener(this)
        rdi_apple.setOnCheckedChangeListener(this)

        val myImageChecked = MyImageChecked()
        rdi_picture_one.setOnCheckedChangeListener(myImageChecked)
        rdi_picture_two.setOnCheckedChangeListener(myImageChecked)
        rdi_picture_three.setOnCheckedChangeListener(myImageChecked)

    }

    inner class MyImageChecked : CompoundButton.OnCheckedChangeListener {
        @SuppressLint("SetTextI18n")
        override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
            if (buttonView!!.id == R.id.rdi_picture_one)
                text_result.text = "Image One"
            else if (buttonView.id == R.id.rdi_picture_two)
                text_result.text = "Image Two"
            else if (buttonView.id == R.id.rdi_picture_three)
                text_result.text = "Image Three"
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        text_result.text = buttonView!!.text
    }
}