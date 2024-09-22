package br.com.book.api.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class EurekaClientController {

    @GetMapping("/eureka-server-clients")
    fun greeting(): String {
        return "Hello from Eureka Client!"
    }
}