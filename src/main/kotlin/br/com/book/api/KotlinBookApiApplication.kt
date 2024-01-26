package br.com.book.api

import br.com.book.api.service.orderNumber.GenerateOrder
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinBookApiApplication

fun main(args: Array<String>) {
	runApplication<KotlinBookApiApplication>(*args)
}
