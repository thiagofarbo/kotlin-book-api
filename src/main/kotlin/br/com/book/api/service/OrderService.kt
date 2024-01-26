package br.com.book.api.service

import br.com.book.api.service.orderNumber.GenerateOrder
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service

@Service
class OrderService(private val generateOrder: GenerateOrder) {
    fun getOrderNumber(): String = runBlocking {
        println("Início da execução")

        val orderNumber:String =  generateOrder.generate()

        println("Execução enquanto a operação assíncrona está em andamento")


        println("Fim da execução. Resultado: $orderNumber")

        orderNumber
    }
}