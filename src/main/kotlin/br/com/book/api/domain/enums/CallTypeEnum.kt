package br.com.book.api.domain.enums

import br.com.book.api.exception.BookException
import lombok.Getter
import java.util.*

@Getter
enum class CallTypeEnum(val typeCall: String){

    BUY("Buy"),
    RENT("Rent");

    val description: String = ""
    companion object {
        fun getCallType(type: String): CallTypeEnum {
            return Arrays.stream(CallTypeEnum.values())
                .filter { t -> t.description.equals(type) }
                .findFirst()
                .orElseThrow { BookException("Call Type not found") }
        }

        fun getCallTypeByEnum(type: CallTypeEnum): CallTypeEnum {
            return Arrays.stream(CallTypeEnum.values())
                .filter { t -> t.equals(type) }
                .findFirst()
                .orElseThrow { BookException("Call Type not found") }
        }
    }
}