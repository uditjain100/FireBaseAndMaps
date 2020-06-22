package udit.programmer.co.firebase.FireBase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_spinner.*
import udit.programmer.co.firebase.R

class SpinnerActivity : AppCompatActivity()
//    , FirebaseLoadDone
{

//    lateinit var quizRef: DatabaseReference
//    lateinit var firebaseLoadDone: FirebaseLoadDone

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner)

        val list = listOf<String>("One", "Two", "Three")
        val adapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, list)
        searchable_spinner.adapter = adapter

//        firebaseLoadDone = this
//        quizRef = FirebaseDatabase.getInstance().reference
//        quizRef.addValueEventListener(object : ValueEventListener {
//            var EDMTQuizList: MutableList<EDMTQuiz> = ArrayList()
//            override fun onCancelled(p0: DatabaseError) {
//                firebaseLoadDone.onFirebaseLoadFailed(p0.message)
//            }
//
//            override fun onDataChange(p0: DataSnapshot) {
//                for (quizSnapshot in p0.children) {
//                    EDMTQuizList.add(quizSnapshot.getValue<EDMTQuiz>(EDMTQuiz::class.java)!!)
//                }
//                firebaseLoadDone.onFirebaseLoadSuccess(EDMTQuizList)
//            }
//        })

    }

//    override fun onFirebaseLoadSuccess(EDMTQuizList: List<EDMTQuiz>) {
//        val question = getQuestion(EDMTQuizList)
//        val adapter = ArrayAdapter<String>(
//            this,
//            android.R.layout.simple_expandable_list_item_1,
//            question
//        )
//        searchable_spinner.adapter = adapter
//    }
//
//    private fun getQuestion(EDMTQuizList: List<EDMTQuiz>): List<String> {
//        val list = ArrayList<String>()
//        for (movie in EDMTQuizList)
//            list.add(movie.questionText!!)
//        return list
//    }
//
//    override fun onFirebaseLoadFailed(message: String?) {
//    }
}