package udit.programmer.co.firebase.FireBase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_fire_base.*
import kotlinx.android.synthetic.main.activity_main.*
import udit.programmer.co.firebase.R

class FireBaseActivity : AppCompatActivity() {

    val auth by lazy {
        FirebaseAuth.getInstance()
    }
    val db by lazy {
        FirebaseDatabase.getInstance()
    }
    val users by lazy {
        db.getReference("Users")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fire_base)

        web_btn.setOnClickListener {
            startActivity(Intent(this, WebActivity::class.java))
        }

        sign_up.setOnClickListener {
            auth.createUserWithEmailAndPassword(
                email_et.text.toString(),
                password_et.text.toString()
            )
                .addOnSuccessListener {
                    var user = User(
                        name_et.text.toString(),
                        email_et.text.toString(),
                        password_et.text.toString(),
                        phone_et.text.toString()
                    )
                    users.child(FirebaseAuth.getInstance().currentUser!!.uid)
                        .setValue(user)
                        .addOnSuccessListener {
                            Snackbar.make(
                                root_layout,
                                "Registered Successfully",
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                        .addOnFailureListener {
                            Snackbar.make(
                                root_layout,
                                "FAILED : " + it.toString(),
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                }
                .addOnFailureListener {
                    Snackbar.make(
                        root_layout,
                        "FAILED : " + it.toString(),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
        }

    }
}
