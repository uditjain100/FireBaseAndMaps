package udit.programmer.co.firebase.RoomDatabase

import androidx.room.*

@Entity
data class Student(
    var name: String,
    var address: String,
    var phone_no: String,
    var age: Int,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)

