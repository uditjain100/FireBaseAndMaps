package udit.programmer.co.firebase.RoomDatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_room.*
import kotlinx.coroutines.*
import udit.programmer.co.firebase.R

class RoomActivity : AppCompatActivity() {

    val db by lazy {
        Room.databaseBuilder(this, AppDatabase::class.java, "Student.db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        save_btn.setOnClickListener {

            GlobalScope.launch(Dispatchers.IO) {
                db.studentDao().insert(Student("Udit", "Baghpat", "9205508109", 20))
            }
        }

        fetch_btn.setOnClickListener {
            runBlocking {
                val list = GlobalScope.async(Dispatchers.IO) { db.studentDao().getAllStudents() }
                if (list.await().isNotEmpty()) {
                    with(list.await()[0]) {
                        name_tv.setText(name)
                        add_tv.setText(address)
                        phone_tv.setText(phone_no)
                        age_tv.setText(age.toString())
                    }
                }
            }
        }
    }
}
