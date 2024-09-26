package com.isra.sprint13

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.DispatcherServlet

@SpringBootApplication
class Sprint13Application

fun main(args: Array<String>) {
    val ctx = runApplication<Sprint13Application>(*args)

    val dispatcherServlet = ctx.getBean("dispatcherServlet") as DispatcherServlet
    dispatcherServlet.setThrowExceptionIfNoHandlerFound(true)
}
