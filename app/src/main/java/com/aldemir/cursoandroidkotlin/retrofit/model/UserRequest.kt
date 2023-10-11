package com.aldemir.cursoandroidkotlin.retrofit.model

import com.google.gson.annotations.SerializedName

data class UserRequest(
    @SerializedName("username")
    var email: String,
    @SerializedName("password")
    var password: String
)