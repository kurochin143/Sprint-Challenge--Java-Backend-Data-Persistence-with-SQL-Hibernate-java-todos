package com.isra.sprint13.controller

import com.isra.sprint13.model.ToDo
import com.isra.sprint13.service.ToDoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class ToDoController(private val todoService: ToDoService) {

    @PostMapping("/users/todo/{userid}")
    fun addTodo(@RequestBody toDo: ToDo, @PathVariable("userid") userid: Long): ResponseEntity<*> {
        todoService.save(toDo, userid)

        return ResponseEntity("""{"message": "added"}""", HttpStatus.CREATED)
    }

}