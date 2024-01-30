package br.com.book.api.service

import br.com.book.api.service.orderNumber.GenerateOrder
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service

@Service
class OrderService(private val generateOrder: GenerateOrder) {
    fun getOrderNumber(): String = runBlocking {
        println("Start of execution ")
        val orderNumber:String =  generateOrder.generate()

        println(" asynchronous operation is in progress")

        println("End of execution, result : $orderNumber")

        orderNumber
    }
}