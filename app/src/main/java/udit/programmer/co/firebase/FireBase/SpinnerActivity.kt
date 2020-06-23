package udit.programmer.co.firebase.FireBase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_spinner.*
import udit.programmer.co.firebase.R

class SpinnerActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    private var dataList: MutableList<String> = mutableListOf()
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var listener: ValueEventListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner)

//        val list = listOf<String>("One", "Two", "Three")
//        val adapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, list)
//        searchable_spinner.adapter = adapter

        databaseReference = FirebaseDatabase.getInstance().reference
        adapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, dataList)
        searchable_spinner.adapter = adapter

        btn_click.setOnClickListener {
            additionWork()
        }
        retrieveData()
    }

    private fun additionWork() {
        var data = tvv.text.toString().trim()
        databaseReference.push().setValue(data).addOnCompleteListener {
            tvv.setText("")
            dataList.clear()
            retrieveData()
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "Data added Successfully", Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Data addition Failed", Toast.LENGTH_LONG).show()
        }
    }

    private fun retrieveData() {
        listener = databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                for (dataSnapshot in p0.children)
                    dataList.add(dataSnapshot.value.toString())
                adapter.notifyDataSetChanged()
            }
        })
    }

}