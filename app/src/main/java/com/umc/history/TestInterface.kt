package com.umc.history

import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.Body

interface TestInterface {
    @GET("/common/quiz/{category}")
    fun getTest(
        @Header("Authorization") Authorization : String,
        @Path("category") category : String
    ) : Call<GetTestResponse>

}