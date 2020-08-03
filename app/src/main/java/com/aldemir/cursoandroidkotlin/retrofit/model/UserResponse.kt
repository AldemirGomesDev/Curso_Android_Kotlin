package com.aldemir.cursoandroidkotlin.retrofit.model

import com.google.gson.annotations.SerializedName

data class UserResponse (
    @SerializedName("status")
    var status : Int,
    @SerializedName("message")
    var message: String,
    @SerializedName("user")
    var user: User,
    @SerializedName("token")
    var token: String
) {
    data class User (
        @SerializedName("id")
        var id : Int,
        @SerializedName("name")
        var name: String,
        @SerializedName("email")
        var email: String,
        @SerializedName("islogged")
        var islogged: Boolean,
        @SerializedName("createdAt")
        var createdAt: Boolean,
        @SerializedName("updatedAt")
        var updatedAt: Boolean
    )
}