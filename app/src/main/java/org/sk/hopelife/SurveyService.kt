package org.sk.hopelife

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SurveyService {
    @GET("api/getSurvey")
    fun get(@Query("sid") sid: String?): Call<SurveyResponse>
}