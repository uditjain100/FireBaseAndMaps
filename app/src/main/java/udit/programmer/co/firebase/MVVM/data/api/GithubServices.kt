package udit.programmer.co.firebase.MVVM.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import udit.programmer.co.firebase.MVVM.data.models.User

interface GithubServices {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    @GET("search/users")
    suspend fun searchUsers(@Query("q") name: String): Response<List<User>>

}