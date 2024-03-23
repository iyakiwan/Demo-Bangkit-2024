package com.mufti.bangkit.learn.demobangkit2024.data.remote.mapper

import com.mufti.bangkit.learn.demobangkit2024.data.remote.response.UserResponse
import com.mufti.bangkit.learn.demobangkit2024.model.User

object UserMapper {
    fun mapListUserResponseToListUser(userResponse: UserResponse?): List<User> {
        val users = ArrayList<User>()

        userResponse?.data?.map {
            users.add(
                User(
                    id = it.id ?: 0,
                    avatar = it.avatar.orEmpty(),
                    email = it.email.orEmpty(),
                    firstName = it.firstName.orEmpty(),
                    lastName = it.lastName.orEmpty()
                )
            )
        }
        return users
    }
}
