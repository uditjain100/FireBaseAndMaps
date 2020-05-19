package udit.programmer.co.firebase.RoomDatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Student::class], version = 0)
abstract class AppDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao
}