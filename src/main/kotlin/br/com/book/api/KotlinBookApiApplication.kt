package br.com.book.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@EnableEurekaClient
@SpringBootApplication
class KotlinBookApiApplication

fun main(args: Array<String>) {
	runApplication<KotlinBookApiApplication>(*args)
}
