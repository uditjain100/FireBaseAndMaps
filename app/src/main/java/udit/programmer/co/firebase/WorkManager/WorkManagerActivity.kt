package udit.programmer.co.firebase.WorkManager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.activity_work_manager.*
import udit.programmer.co.firebase.R

class WorkManagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)

        work_btn.setOnClickListener {
            setUpGithubWorker()
        }
    }

    private fun setUpGithubWorker() {
        val worker = OneTimeWorkRequestBuilder<GithubWorker>().build()
        WorkManager.getInstance(this).enqueue(worker)
    }
}
