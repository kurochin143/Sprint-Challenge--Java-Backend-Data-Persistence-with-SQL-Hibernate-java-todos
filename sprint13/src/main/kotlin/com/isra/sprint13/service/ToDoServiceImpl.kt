package com.isra.sprint13.service

import com.isra.sprint13.model.ToDo
import com.isra.sprint13.repository.ToDoRepository
import com.isra.sprint13.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

@Service
class ToDoServiceImpl(private val todoRepository: ToDoRepository, private val userRepository: UserRepository) : ToDoService {

    @Transactional
    override fun save(toDo: ToDo, userid: Long) {

        val user = userRepository.findById(userid)
                .orElseThrow { EntityNotFoundException("User id $userid cannot be found") }

//        val userTodos = user.todos as MutableList
//        userTodos.add(toDo)

        userRepository.save(user)
    }

}