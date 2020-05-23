package udit.programmer.co.firebase.MVVM.data.api

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrifitClient {

    val gson =
        GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
    val retrofitClient = Retrofit.Builder()
        .baseUrl("https://api.github.com/").addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    var api = retrofitClient.create(GithubServices::class.java)
}