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
                .filter { t -> t.description.equals(type) }
                .findFirst()
                .orElseThrow { BookException("Call Type not found") }
        }

        fun getCallTypeByEnum(type: OrderTypeEnum): OrderTypeEnum {
            return Arrays.stream(OrderTypeEnum.values())
                .filter { t -> t.equals(type) }
                .findFirst()
                .orElseThrow { BookException("Call Type not found") }
        }
    }
}