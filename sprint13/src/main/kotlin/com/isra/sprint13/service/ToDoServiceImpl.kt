package com.isra.sprint13.service

import com.isra.sprint13.model.ToDo
import com.isra.sprint13.repository.ToDoRepository
import com.isra.sprint13.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

@Service
class ToDoServiceImpl(private val todoRepository: ToDoRepository, private val userRepository: UserRepository) : ToDoService {

    override fun save(toDo: ToDo, userid: Long): ToDo {
        val user = userRepository.findById(userid)
                .orElseThrow { EntityNotFoundException("User id $userid cannot be found!") }

        val newTodo = ToDo(toDo.description, toDo.datestarted, toDo.completed, user)

        return todoRepository.save(newTodo)

    }

    override fun update(toDo: ToDo, id: Long) {

        val currentToDo = todoRepository.findById(id)
                .orElseThrow { EntityNotFoundException("ToDo id $id cannot be found!") }

        if (toDo.description != null) currentToDo.description = toDo.description
        if (toDo.datestarted != null) currentToDo.datestarted = toDo.datestarted
        if (toDo.completed != null) currentToDo.completed = toDo.completed

        todoRepository.save(currentToDo)
    }

}