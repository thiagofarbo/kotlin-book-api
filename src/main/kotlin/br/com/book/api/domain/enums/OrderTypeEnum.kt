package br.com.book.api.domain.enums

import br.com.book.api.exception.BookException
import lombok.Getter
import java.util.*

@Getter
enum class OrderTypeEnum(val typeCall: String){

    PURCHASE("Purchased"),
    RENT("Rented");

    val description: String = ""
    companion object {
        fun getCallType(type: String): OrderTypeEnum {
            return Arrays.stream(OrderTypeEnum.values())
                .filter { t -> type == t.description }
                .findFirst()
                .orElseThrow { BookException("Order type not found") }
        }

        fun getCallTypeByEnum(type: OrderTypeEnum): OrderTypeEnum {
            return Arrays.stream(OrderTypeEnum.values())
                .filter { t -> t.equals(type) }
                .findFirst()
                .orElseThrow { BookException("Order type not found") }
        }
    }
}