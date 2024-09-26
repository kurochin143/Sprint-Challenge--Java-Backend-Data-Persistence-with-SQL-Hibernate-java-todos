package com.isra.sprint13.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LandingPageController {

    @GetMapping("/")
    fun landingPage(): ResponseEntity<*> {
        return ResponseEntity("Israel Sprint13", HttpStatus.OK)
    }

}