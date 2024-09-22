package br.com.book.api.domain.payment

import lombok.Builder
import lombok.Getter
import lombok.Setter
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.io.Serializable

@Getter
@Setter
@Builder
data class PaymentResponse(var statusCode: Int, var currency: String, var amount: Long?): Serializable {

    constructor() : this(0, "", 0)

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
    }
}
