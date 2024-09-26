package com.isra.sprint13.service

import com.isra.sprint13.model.User
import com.isra.sprint13.model.UserRoles
import com.isra.sprint13.repository.RoleRepository
import com.isra.sprint13.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import javax.persistence.EntityNotFoundException
import java.util.ArrayList
import java.util.function.Consumer


@Service(value = "userService")
class UserServiceImpl : UserDetailsService, UserService {

    @Autowired
    private val userrepos: UserRepository? = null

    @Autowired
    private val rolerepos: RoleRepository? = null

    @Transactional
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userrepos!!.findByUsername(username)
                ?: throw UsernameNotFoundException("Invalid username or password.")
        return org.springframework.security.core.userdetails.User(user.username!!, user.password!!, user.getAuthority())
    }

    @Throws(EntityNotFoundException::class)
    override fun findUserById(id: Long): User {
        return userrepos!!.findById(id)
                .orElseThrow { EntityNotFoundException(java.lang.Long.toString(id)) }
    }

    override fun findCurrent(): User {
        val authentication = SecurityContextHolder.getContext().authentication

        return userrepos!!.findByUsername(authentication.name) ?: throw EntityNotFoundException("user cannot be found")
    }

    override fun findAll(): List<User> {
        val list = ArrayList<User>()
        userrepos!!.findAll().iterator().forEachRemaining(Consumer<User> { list.add(it) })
        return list
    }

    override fun delete(id: Long) {
        if (userrepos!!.findById(id).isPresent) {
            userrepos.deleteById(id)
        } else {
            throw EntityNotFoundException(java.lang.Long.toString(id))
        }
    }

    @Transactional
    override fun save(user: User): User {
        val newUser = User()
        newUser.username = user.username
        newUser.setPasswordEncrypt(user.password)

        val newRoles = ArrayList<UserRoles>()
        for (ur in user.userroles) {
            newRoles.add(UserRoles(newUser, ur.role))
        }
        newUser.userroles = newRoles

        return userrepos!!.save(newUser)
    }

    @Transactional
    override fun update(user: User, id: Long): User {
        val authentication = SecurityContextHolder.getContext().authentication
        val currentUser = userrepos!!.findByUsername(authentication.name)

        if (currentUser != null) {
            if (id == currentUser.userid) {
                if (user.username != null) {
                    currentUser.username = user.username
                }

                if (user.password != null) {
                    currentUser.password = user.password
                }

                if (user.userroles.size > 0) {
                    // with so many relationships happening, I decided to go
                    // with old school queries
                    // delete the old ones
                    rolerepos!!.deleteUserRolesByUserId(currentUser.userid)

                    // add the new ones
                    for (ur in user.userroles) {
                        rolerepos.insertUserRoles(id, ur.role!!.roleid)
                    }
                }

                if (user.todos.size > 0) {
                    for (toDo in user.todos) {
                        val currentUserTodos = currentUser.todos as MutableList
                        currentUserTodos.add(toDo)
                    }
                }

                return userrepos.save(currentUser)
            } else {
                throw EntityNotFoundException(java.lang.Long.toString(id) + " Not current user")
            }
        } else {
            throw EntityNotFoundException(authentication.name)
        }

    }
}
