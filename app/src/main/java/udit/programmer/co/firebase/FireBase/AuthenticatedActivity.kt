package udit.programmer.co.firebase.FireBase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_authenticated.*
import udit.programmer.co.firebase.R
import kotlin.math.sign

class AuthenticatedActivity : AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authenticated)

        mAuth = FirebaseAuth.getInstance()

        signOut_btn.setOnClickListener {
            mAuth.signOut()
            startActivity(Intent(this, PhoneActivity::class.java))
            Toast.makeText(this, "Logged out Successfully :)", Toast.LENGTH_LONG).show()
        }
    }

    override fun onStart() {
        super.onStart()
        if (mAuth.currentUser == null)
            startActivity(Intent(this, PhoneActivity::class.java))
        else
            Toast.makeText(this, "Already Signed In :)", Toast.LENGTH_LONG).show()
    }
}