package udit.programmer.co.firebase.RoomDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

//Data Access Objects
@Dao
interface StudentDao {

    @Insert
    fun insert(student: Student)

    @Insert
    fun insertAll(students: List<Student>)

    @Delete
    fun delete(student: Student)

    @Query("SELECT * FROM Student")
    fun getAllStudents(): LiveData<List<Student>>

    @Query("SELECT * FROM Student WHERE age >= :age")
    fun getStudentsWithAge(age: Int): List<Student>

}