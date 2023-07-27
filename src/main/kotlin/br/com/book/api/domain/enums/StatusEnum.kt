package br.com.book.api.domain.enums

import br.com.book.api.exception.BookException
import lombok.Getter
import java.util.*

@Getter
enum class StatusEnum(val status: String){

    RENTED("Rented"),
    RETURNED("Returned");

    val description: String = ""

    companion object {

        fun getStatus(bookStatus: String): StatusEnum {
            return Arrays.stream(StatusEnum.values())
                .filter { status -> status.description.equals(bookStatus) }
                .findFirst()
                .orElseThrow { BookException("Status Not Found") }
        }
    }
}