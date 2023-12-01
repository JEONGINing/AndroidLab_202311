package com.example.ch15_retrofit

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface NetworkService {
    @GET("api/users")
    fun getUserList(@Query("page") page: String): Call<UserList>

    @GET
    fun getAvatarImage(@Url url: String): Call<ResponseBody>
}