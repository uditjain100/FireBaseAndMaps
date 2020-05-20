package udit.programmer.co.firebase.WorkManager

import retrofit2.Response
import retrofit2.http.GET
import udit.programmer.co.firebase.FireBase.User

interface WorkManagerServices {

    @GET("user")
    suspend fun getUsers(): Response<List<User>>

}