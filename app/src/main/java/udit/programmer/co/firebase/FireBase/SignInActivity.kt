package udit.programmer.co.firebase.FireBase

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_in.*
import udit.programmer.co.firebase.R

class SignInActivity : AppCompatActivity() {

    lateinit var providers: List<AuthUI.IdpConfig>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_sign_in)

        providers = mutableListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.FacebookBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build()
        )

        showSignInOptions()

        sign_out_google.setOnClickListener {
            AuthUI.getInstance().signOut(this)
                .addOnCompleteListener {
                    sign_out_google.isEnabled = false
                    showSignInOptions()
                }.addOnFailureListener {
                    Toast.makeText(this, "${it.message}", Toast.LENGTH_LONG).show()
                }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 12345 && requestCode == Activity.RESULT_OK) {
            val response = IdpResponse.fromResultIntent(data)
            if (requestCode == Activity.RESULT_OK) {
                val user = FirebaseAuth.getInstance().currentUser
                Toast.makeText(this, "${user!!.email}", Toast.LENGTH_LONG).show()
                sign_out_google.isEnabled = true
            } else {
                Toast.makeText(this, "${response!!.error!!.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showSignInOptions() {
        startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTheme(R.style.AppTheme4)
                .build(), 12345
        )
    }
}