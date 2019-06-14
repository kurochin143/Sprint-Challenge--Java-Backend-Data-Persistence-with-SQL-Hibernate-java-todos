package com.isra.sprint13

import com.isra.sprint13.model.Role
import com.isra.sprint13.model.ToDo
import com.isra.sprint13.model.User
import com.isra.sprint13.model.UserRoles
import com.isra.sprint13.repository.RoleRepository
import com.isra.sprint13.repository.ToDoRepository
import com.isra.sprint13.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

import java.util.ArrayList

@Transactional
@Component
class SeedData(internal var rolerepos: RoleRepository, internal var userrepos: UserRepository, internal var todorepos: ToDoRepository) : CommandLineRunner {

    @Throws(Exception::class)
    override fun run(args: Array<String>) {
        val r1 = Role("admin")
        val r2 = Role("user")

        val admins = ArrayList<UserRoles>()
        admins.add(UserRoles(User(), r1))
        admins.add(UserRoles(User(), r2))

        val users = ArrayList<UserRoles>()
        users.add(UserRoles(User(), r2))

        rolerepos.save(r1)
        rolerepos.save(r2)

        //User u1 = new User("barnbarn", "ILuvM4th!", users);
        val u2 = User("admin", "password", admins)
        val u3 = User("Bob", "password", users)
        //User u4 = new User("Jane", "password", users);

        //        // the date and time string should get coverted to a datetime Java data type. This is done in the constructor!
        //        u4.getTodos().add(new ToDo("Finish java-orders-swagger", "2019-01-13 04:04:04", false, u4));
        //        u4.getTodos().add(new ToDo("Feed the turtles", "2019-03-01 04:04:04", false, u4));
        //        u4.getTodos().add(new ToDo("Complete the sprint challenge", "2019-02-22 04:04:04", false, u4));
        //
        val u3Todos = u3.todos as MutableList
        u3Todos.add(ToDo("Walk the dogs", "2019-01-17 04:04:04", false, u3))
        u3Todos.add(ToDo("provide feedback to my instructor", "2019-02-13 04:04:04", false, u3))

        //        userrepos.save(u1);
        userrepos.save(u2)
        userrepos.save(u3)
        //        userrepos.save(u4);
    }
}

