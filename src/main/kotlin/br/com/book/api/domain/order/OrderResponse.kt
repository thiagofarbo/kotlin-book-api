package br.com.book.api.domain.order

import br.com.book.api.domain.Book
import br.com.book.api.domain.Customer
import br.com.book.api.domain.enums.OrderTypeEnum
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import lombok.Builder
import lombok.Getter
import lombok.Setter
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.io.Serializable
import java.time.LocalDate

@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
data class OrderResponse(

    val orderId: String?,

    val callType: OrderTypeEnum,

    val book: Book,

    var customer: Customer,

    val orderDate: LocalDate,

    val returnDate: LocalDate?,

    val quantity : Int?,
):Serializable{
    constructor(
        callType: OrderTypeEnum,
        book: Book,
        customer: Customer,
        orderDate: LocalDate,
        returnDate: LocalDate,
        quantity: Int
    ) : this(null, callType, book, customer, orderDate, returnDate, quantity)

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
    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
    }
}

