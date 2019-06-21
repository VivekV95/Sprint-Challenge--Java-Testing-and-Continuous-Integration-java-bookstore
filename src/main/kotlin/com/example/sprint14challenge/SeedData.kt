package com.example.sprint14challenge

import com.example.sprint14challenge.model.Role
import com.example.sprint14challenge.model.User
import com.example.sprint14challenge.model.UserRoles
import com.example.sprint14challenge.service.RoleService
import com.example.sprint14challenge.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

import java.util.ArrayList

//@Transactional
//@Component
class SeedData : CommandLineRunner {

    @Autowired
    private lateinit var roleService: RoleService

    @Autowired
    private lateinit var userService: UserService


    @Throws(Exception::class)
    override fun run(args: Array<String>) {
        val r1 = Role("admin")
        val r2 = Role("user")
        val r3 = Role("data")

        roleService.save(r1)
        roleService.save(r2)
        roleService.save(r3)

        // admin, data, user
        val admins = ArrayList<UserRoles>()
        admins.add(UserRoles(User(), r1))
        admins.add(UserRoles(User(), r2))
        admins.add(UserRoles(User(), r3))
        val u1 = User("admin", "password", admins)
        userService.save(u1)

        // data, user
        val datas = ArrayList<UserRoles>()
        datas.add(UserRoles(User(), r3))
        datas.add(UserRoles(User(), r2))
        val u2 = User("cinnamon", "1234567", datas)
        userService.save(u2)

        // user
        var users = ArrayList<UserRoles>()
        users.add(UserRoles(User(), r2))
        val u3 = User("barnbarn", "ILuvM4th!", users)
        userService.save(u3)

        users = ArrayList()
        users.add(UserRoles(User(), r2))
        val u4 = User("Bob", "password", users)
        userService.save(u4)

        users = ArrayList()
        users.add(UserRoles(User(), r2))
        val u5 = User("Jane", "password", users)
        userService.save(u5)
    }
}