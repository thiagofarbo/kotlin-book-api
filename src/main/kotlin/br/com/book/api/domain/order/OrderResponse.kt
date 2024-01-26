package br.com.book.api.domain.order

import br.com.book.api.domain.Book
import br.com.book.api.domain.Customer
import br.com.book.api.domain.enums.OrderTypeEnum
import lombok.Builder
import lombok.Getter
import lombok.Setter
import java.time.LocalDate

@Getter
@Setter
@Builder
data class OrderResponse(

    val orderId: String?,

    val callType: OrderTypeEnum,

    val book: Book,

    var customer: Customer,

    val orderDate: LocalDate,

    val returnDate: LocalDate?,

    val quantity : Int?,
){
    constructor(
        callType: OrderTypeEnum,
        book: Book,
        customer: Customer,
        orderDate: LocalDate,
        returnDate: LocalDate,
        quantity: Int
    ) : this(null, callType, book, customer, orderDate, returnDate, quantity)

    override fun toString(): String {
        return "OrderResponse(orderId=$orderId, callType='$callType', book=$book, customer=$customer, orderDate=$orderDate, returnDate=$returnDate, quantity=$quantity)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is OrderResponse) return false

        if (orderId != other.orderId) return false
        if (callType != other.callType) return false
        if (book != other.book) return false
        if (customer != other.customer) return false
        if (orderDate != other.orderDate) return false
        if (returnDate != other.returnDate) return false
        if (quantity != other.quantity) return false

        return true
    }

    override fun hashCode(): Int {
        var result = orderId?.hashCode() ?: 0
        result = 31 * result + callType.hashCode()
        result = 31 * result + book.hashCode()
        result = 31 * result + customer.hashCode()
        result = 31 * result + orderDate.hashCode()
        result = 31 * result + returnDate.hashCode()
        result = 31 * result + quantity.hashCode()
        return result
    }
}

