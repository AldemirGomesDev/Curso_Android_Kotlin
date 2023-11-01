package com.aldemir.cursoandroidkotlin.retrofit.api

import com.aldemir.cursoandroidkotlin.retrofit.model.UserRequest
import com.aldemir.cursoandroidkotlin.retrofit.model.UserResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserAPI {
    @POST("auth/login")
    fun onLogin(@Body userRequest: UserRequest): Call<UserResponse>

    @GET("products/categories")
    suspend fun getCategory(): Response<List<String>>

}