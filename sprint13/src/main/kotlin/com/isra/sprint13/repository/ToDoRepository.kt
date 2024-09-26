package com.isra.sprint13.repository

import com.isra.sprint13.model.ToDo
import org.springframework.data.repository.CrudRepository

interface ToDoRepository : CrudRepository<ToDo, Long> {

}