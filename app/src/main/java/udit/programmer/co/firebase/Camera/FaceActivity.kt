package udit.programmer.co.firebase.Camera

import android.Manifest
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_face.*
import udit.programmer.co.firebase.R

class FaceActivity : AppCompatActivity() {

    lateinit var firebaseStorage: StorageReference
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_face)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
            || ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.INTERNET
            ) != PackageManager.PERMISSION_GRANTED

        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.INTERNET),
                2222
            )
        }

        firebaseStorage = FirebaseStorage.getInstance().reference
        progressDialog = ProgressDialog(this)
        upload_btn.setOnClickListener {
            startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE), 1234)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1234 && resultCode == RESULT_OK) {
            Log.d("Ceased Meteor", " Yo man 000")
            progressDialog.setMessage("Uploading Image ... ")
            progressDialog.show()
            Log.d("Ceased Meteor", " Yo man 001")
            val uri = data!!.data
            val filePath = firebaseStorage.child("Photos").child(uri!!.lastPathSegment!!)
            Log.d("Ceased Meteor", " Yo man 002")
            filePath.putFile(uri).addOnSuccessListener {
                Log.d("Ceased Meteor", " Yo man 003")
                progressDialog.dismiss()
                Log.d("Ceased Meteor", " Yo man 004")
                Toast.makeText(this, "Uploading Finished", Toast.LENGTH_LONG).show()

            }.addOnFailureListener {
                Log.d("Ceased Meteor", " Yo man 005")
                Toast.makeText(this, "Uploading Failed", Toast.LENGTH_LONG).show()
            }
        }
    }

}
