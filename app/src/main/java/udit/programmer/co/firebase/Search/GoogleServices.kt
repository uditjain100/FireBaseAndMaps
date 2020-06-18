package udit.programmer.co.firebase.Search

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleServices {

    @GET("complete/search")
    fun getSuggestFromYoutube(
        @Query("q") query: String,
        @Query("client") client: String,
        @Query("hl") language: String,
        @Query("ds") restrict: String
    ): Observable<String>

    @GET("complete/search")
    fun getSuggestFromGoogle(
        @Query("q") query: String,
        @Query("client") client: String,
        @Query("hl") language: String
    ): Observable<String>

}