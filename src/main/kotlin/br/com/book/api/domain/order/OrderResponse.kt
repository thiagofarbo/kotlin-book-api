package br.com.book.api.domain.order

import br.com.book.api.domain.Book
import br.com.book.api.domain.Renter
import lombok.Builder
import lombok.Getter
import lombok.Setter
import java.time.LocalDate

@Getter
@Setter
@Builder
data class OrderResponse(

    val orderId: String?,

    val callType: String,

    val book: Book,

    var renter: Renter,

    val rentalDate: LocalDate,

    val returnDate: LocalDate,
){
    constructor(
        callType: String,
        book: Book,
        renter: Renter,
        rentalDate: LocalDate,
        returnDate: LocalDate
    ) : this(null, callType, book, renter, rentalDate, returnDate)

    override fun toString(): String {
        return "OrderResponse(orderId=$orderId, callType='$callType', book=$book, renter=$renter, rentalDate=$rentalDate, returnDate=$returnDate)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is OrderResponse) return false

        if (orderId != other.orderId) return false
        if (callType != other.callType) return false
        if (book != other.book) return false
        if (renter != other.renter) return false
        if (rentalDate != other.rentalDate) return false
        if (returnDate != other.returnDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = orderId?.hashCode() ?: 0
        result = 31 * result + callType.hashCode()
        result = 31 * result + book.hashCode()
        result = 31 * result + renter.hashCode()
        result = 31 * result + rentalDate.hashCode()
        result = 31 * result + returnDate.hashCode()
        return result
    }
}

