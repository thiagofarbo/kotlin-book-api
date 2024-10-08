package br.com.book.api.service

import br.com.book.api.context.CallStrategy
import br.com.book.api.domain.enums.OrderTypeEnum
import br.com.book.api.domain.order.OrderRequest
import br.com.book.api.domain.order.OrderResponse
import br.com.book.api.service.coupon.CouponService
import br.com.book.api.service.message.KafkaProducerService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate

@Service
class Call (
    private val bookService: BookService,

    private val couponService: CouponService,

    private val kafkaService: KafkaProducerService,


    ): CallStrategy{

    @Value("\${topic.book.producer}")
    private lateinit var topic : String

    lateinit var order: OrderResponse

    override fun createOrder(orderRequest: OrderRequest): OrderResponse {

        val callType = OrderTypeEnum.getCallTypeByEnum(orderRequest.callType)

        if(callType == OrderTypeEnum.RENT){

            val price = couponService.calculateOrderPrice(orderRequest.price, orderRequest.quantity, orderRequest.voucherCode)

            val rentBook = bookService.rentBook(orderRequest.isbn, orderRequest.cpf, orderRequest.quantity, LocalDate.now().plusDays(10), price)

            var returnDate: LocalDate = LocalDate.now()

            rentBook.returnDate?.let {
                returnDate = rentBook.returnDate
            }

            order = OrderResponse(rentBook.id, callType, rentBook.book, rentBook.customer, rentBook.orderDate, returnDate, orderRequest.quantity, price)

            kafkaService.sendToKafkaJson(topic, order)

        }else if (callType == OrderTypeEnum.PURCHASE){

            val price = couponService.calculateOrderPrice(orderRequest.price, orderRequest.quantity, orderRequest.voucherCode)

            val bookPurchased = bookService.purchase(orderRequest.isbn, orderRequest.cpf, orderRequest.quantity, price, orderRequest.isVirtualBook)

            order = OrderResponse(bookPurchased.id, callType, bookPurchased.book, bookPurchased.customer, bookPurchased.orderDate, null, orderRequest.quantity, price)

            //Don't update warehouse due to the book being virtual.
            if(!orderRequest.isVirtualBook){
                kafkaService.sendToKafkaJson(topic, order)
            }
        }
         return order
    }
}