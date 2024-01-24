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
}

