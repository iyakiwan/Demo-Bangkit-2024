package com.mufti.bangkit.learn.demobangkit2024.utils

import com.mufti.bangkit.learn.demobangkit2024.model.User

object DataDummy {
    fun generateDummyUsers(): List<User> {
        val users = ArrayList<User>()
        for (i in 0..10) {
            val news = User(
                id = i,
                email = "test@mail.com",
                firstName =  "Tester",
                lastName =  "$i",
                avatar =  "https://reqres.in/img/faces/7-image.jpg",
            )
            users.add(news)
        }
        return users
    }
}