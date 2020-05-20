package udit.programmer.co.firebase.WorkManager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import kotlinx.android.synthetic.main.activity_work_manager.*
import udit.programmer.co.firebase.R
import java.util.concurrent.TimeUnit

class WorkManagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)

        work_btn.setOnClickListener {
            setUpGithubWorker()
        }
    }

    private fun setUpGithubWorker() {

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .build()

//        val worker = OneTimeWorkRequestBuilder<GithubWorker>()
//            .setInitialDelay(4, TimeUnit.MINUTES)
//            .setConstraints(constraints)
//            .build()

        val worker =
//            PeriodicWorkRequestBuilder<GithubWorker>(PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS)
            PeriodicWorkRequestBuilder<GithubWorker>(1, TimeUnit.DAYS)
                .setInitialDelay(4, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build()

        WorkManager.getInstance(this).enqueue(worker)

    }
}
