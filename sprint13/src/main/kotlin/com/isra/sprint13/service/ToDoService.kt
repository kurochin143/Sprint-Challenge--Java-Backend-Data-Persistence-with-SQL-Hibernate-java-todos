package com.isra.sprint13.service

import com.isra.sprint13.model.ToDo

interface ToDoService {

    fun save(toDo: ToDo, userid: Long)
}