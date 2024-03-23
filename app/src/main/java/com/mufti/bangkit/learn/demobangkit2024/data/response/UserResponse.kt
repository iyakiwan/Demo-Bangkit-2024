package com.mufti.bangkit.learn.demobangkit2024.data.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @field:SerializedName("data")
    val data: List<DataItem>? = null,
) {
    data class DataItem(

        @field:SerializedName("last_name")
        val lastName: String? = null,

        @field:SerializedName("id")
        val id: Int? = null,

        @field:SerializedName("avatar")
        val avatar: String? = null,

        @field:SerializedName("first_name")
        val firstName: String? = null,

        @field:SerializedName("email")
        val email: String? = null
    )
}
