package udit.programmer.co.firebase.FireBase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_phone.*
import udit.programmer.co.firebase.R
import java.util.concurrent.TimeUnit

class PhoneActivity : AppCompatActivity() {

    lateinit var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone)

        mAuth = FirebaseAuth.getInstance()

        verify_btn.setOnClickListener {
            verificationWork()
        }

    }

    private fun verificationCallbacks() {
        mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                signIn(p0)
            }

            override fun onVerificationFailed(p0: FirebaseException) {
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
            }
        }
    }

    private fun signIn(credential: PhoneAuthCredential) {
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Logged In Successfully :)", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, AuthenticatedActivity::class.java))
                }
            }
    }

    private fun verificationWork() {
        verificationCallbacks()
        val phoneNo = number_et.text.toString()
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phoneNo, 60, TimeUnit.SECONDS, this, mCallbacks
        )
    }


}