package br.com.book.api.service

import br.com.book.api.context.CallStrategy
import br.com.book.api.domain.enums.OrderTypeEnum
import br.com.book.api.domain.order.OrderRequest
import br.com.book.api.domain.order.OrderResponse
import br.com.book.api.exception.CustomerException
import br.com.book.api.repository.CustomerRepository
import br.com.book.api.service.message.KafkaService
import br.com.book.api.service.orderNumber.GenerateOrder
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service
import org.springframework.web.servlet.function.ServerResponse.async
import java.time.LocalDate

@Service
class Call (
    private val bookService: BookService,

    private val kafkaService: KafkaService,

    private val orderService: OrderService

): CallStrategy{

    lateinit var order: OrderResponse

    override fun createOrder(orderRequest: OrderRequest): OrderResponse {

        val callType = OrderTypeEnum.getCallTypeByEnum(orderRequest.callType)

        if(callType == OrderTypeEnum.RENT){

            val rentBook = bookService.rentBook(orderRequest.isbn, orderRequest.cpf, LocalDate.now().plusDays(10))

            var returnDate: LocalDate = LocalDate.now()

            rentBook.returnDate?.let {
                returnDate = rentBook.returnDate
            }
            order = OrderResponse(rentBook.id, callType, rentBook.book, rentBook.customer, rentBook.orderDate, returnDate, orderRequest.quantity)

            kafkaService.sendToKafkaJson(order)

        }else if (callType == OrderTypeEnum.PURCHASE){

            val bookPurchased = bookService.purchase(orderRequest.isbn, orderRequest.cpf, orderRequest.quantity)

            order = OrderResponse(bookPurchased.id, callType, bookPurchased.book, bookPurchased.customer, bookPurchased.orderDate, null, orderRequest.quantity)

            kafkaService.sendToKafkaJson(order)
        }
         return order
    }
}