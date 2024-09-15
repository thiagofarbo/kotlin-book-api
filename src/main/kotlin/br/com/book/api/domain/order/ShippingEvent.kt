package br.com.book.api.domain.order

import br.com.book.api.domain.enums.OrderStatusEvent
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Builder
import lombok.Getter
import lombok.Setter
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.format.annotation.DateTimeFormat
import java.io.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Document(collection = "shipping-event")
data class ShippingEvent @JsonCreator constructor(
    @Id
    @JsonProperty("id") val id: String,
    @JsonProperty("orderId") val orderId: String,
    @JsonProperty("orderStatus") val orderStatus: OrderStatusEvent,
    @JsonProperty("details") val details: String,
    @JsonProperty("eventTimestamp")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    val eventTimestamp: LocalDateTime
) : Serializable {
    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
    }
}


