package udit.programmer.co.firebase.WorkManager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GithubWorker(val context: Context, val params: WorkerParameters) :
    CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {

        val response = withContext(Dispatchers.IO) { RetrofitClient.api.getUsers() }
        return if (response.isSuccessful) {
            Result.retry()
        } else {
            Result.failure()
        }

    }
}