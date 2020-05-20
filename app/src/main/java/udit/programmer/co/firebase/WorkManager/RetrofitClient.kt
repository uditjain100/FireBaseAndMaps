package udit.programmer.co.firebase.WorkManager

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val gson =
        GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
    val retrofitClient = Retrofit.Builder()
        .baseUrl("").addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    val api = retrofitClient.create(WorkManagerServices::class.java)
}