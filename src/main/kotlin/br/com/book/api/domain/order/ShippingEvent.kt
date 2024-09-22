package br.com.book.api.domain.order

import br.com.book.api.domain.enums.OrderStatusEvent
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.format.annotation.DateTimeFormat
import java.io.Serializable
import java.time.LocalDateTime


@Document(collection = "shipping-event")
data class ShippingEvent(
    @Id
    val id: String? = null,
    val orderId: String,
    val orderStatus: OrderStatusEvent,
    val details: String,
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    val eventTimestamp: LocalDateTime
) : Serializable {

    // Secondary constructor without the 'id' field
    constructor(
        orderId: String,
        orderStatus: OrderStatusEvent,
        details: String,
        eventTimestamp: LocalDateTime
    ) : this(null, orderId, orderStatus, details, eventTimestamp)

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
    }
}


