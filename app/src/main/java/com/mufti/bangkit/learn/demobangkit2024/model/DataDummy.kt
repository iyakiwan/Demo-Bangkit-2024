package com.mufti.bangkit.learn.demobangkit2024.model

object DataDummy {
    fun dataDummyUser(): List<User> {
        val userList = ArrayList<User>()
        for (i in 0..10) {
            val user = User(
                id = i,
                email = "michael.lawson@reqres.in",
                firstName = "Michael",
                lastName = "Lawson",
                avatar = "https://reqres.in/img/faces/7-image.jpg"
            )
            userList.add(user)
        }
        return userList
    }
}
