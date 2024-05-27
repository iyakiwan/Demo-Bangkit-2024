package com.mufti.bangkit.learn.demobangkit2024.data.remote.mapper

import com.mufti.bangkit.learn.demobangkit2024.data.local.entity.UserEntity
import com.mufti.bangkit.learn.demobangkit2024.data.remote.response.UserResponse
import com.mufti.bangkit.learn.demobangkit2024.model.User

object UserMapper {
    fun mapListUserResponseToListUserEntity(userResponse: UserResponse?): List<UserEntity> {
        val users = ArrayList<UserEntity>()

        userResponse?.data?.map {
            users.add(
                UserEntity(
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

    fun mapListUserEntityToListUser(listUserEntity: List<UserEntity>): List<User> {
        return listUserEntity.map { it ->
            User(
                id = it.id,
                avatar = it.avatar,
                email = it.email,
                firstName = it.firstName,
                lastName = it.lastName
            )
        }
    }

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
