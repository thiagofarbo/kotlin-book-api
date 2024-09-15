package br.com.book.api.service

import br.com.book.api.domain.Order
import br.com.book.api.repository.OrderRepository
import br.com.book.api.service.orderNumber.GenerateOrder
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service

@Service
class OrderService(private val generateOrder: GenerateOrder, private val orderRepository: OrderRepository) {

    fun getOrderNumber(): String = runBlocking {
        println("Start of execution ")
        val orderNumber:String =  generateOrder.generate()

        println(" asynchronous operation is in progress")

        println("End of execution, result : $orderNumber")

        orderNumber
    }

    fun getOrderById(orderId: String) : Order{
        val order = orderRepository.findOrderById(orderId)
        return order
    }
}