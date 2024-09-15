package br.com.book.api.domain.payment

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.NoArgsConstructor
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.io.Serializable

@Builder
@AllArgsConstructor
@NoArgsConstructor
data class PaymentRequest (
                            val currency: String,
                            val amount: Long,
                            val addressLine1: String
): Serializable {
    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
    }
}