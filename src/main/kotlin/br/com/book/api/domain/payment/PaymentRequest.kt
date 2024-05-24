package br.com.book.api.domain.payment

import lombok.Builder
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.io.Serializable

@Builder
data class PaymentRequest (
                            val currency: String,
                            val amount: Long,
                            val addressLine1: String
): Serializable {
    constructor() : this("", 0, "")

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
    }
}