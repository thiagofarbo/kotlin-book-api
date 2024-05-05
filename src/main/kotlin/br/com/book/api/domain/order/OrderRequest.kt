package br.com.book.api.domain.order

import br.com.book.api.domain.enums.OrderTypeEnum
import lombok.Builder
import org.springframework.format.annotation.DateTimeFormat
import java.math.BigDecimal
import java.time.LocalDate

@Builder
data class OrderRequest(
    val isbn: String,
    val cpf:String,
    val callType: OrderTypeEnum,
    val quantity: Int,
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    val returnDate: LocalDate?,
    val voucherCode: String?,
    val price: BigDecimal
)


