package br.com.book.api.service

import br.com.book.api.context.CallStrategy
import br.com.book.api.domain.enums.CallTypeEnum
import br.com.book.api.domain.order.OrderRequest
import br.com.book.api.domain.order.OrderResponse
import br.com.book.api.service.message.KafkaService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class Call (
    private val bookService: BookService,

    private val kafkaService: KafkaService

): CallStrategy{

    lateinit var order: OrderResponse

    override fun createOrder(orderRequest: OrderRequest): OrderResponse {

        val callType = CallTypeEnum.getCallTypeByEnum(orderRequest.callType)

        if(callType == CallTypeEnum.RENT){

            val rentBook = bookService.rentBook(orderRequest.isbn, orderRequest.cpf, LocalDate.now().plusDays(10))

            var returnDate: LocalDate = LocalDate.now()

            rentBook.returnDate?.let {
                returnDate = rentBook.returnDate
            }

            order = OrderResponse("", "", rentBook.book, rentBook.renter, rentBook.rentalDate, returnDate)

            kafkaService.sendToKafkaJson(order)


        }else if (orderRequest.callType.equals(CallTypeEnum.BUY.description)){
//          Not implemented yet
        }
         return order

    }

}