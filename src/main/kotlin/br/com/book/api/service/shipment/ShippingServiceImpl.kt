package br.com.book.api.service.shipment

import br.com.book.api.domain.enums.OrderStatusEvent
import br.com.book.api.domain.order.ShippingEvent
import br.com.book.api.repository.ShippingEventRepository
import br.com.book.api.service.message.KafkaProducerService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class ShippingServiceImpl(private val shippingEventRepository: ShippingEventRepository, private val kafkaService: KafkaProducerService): ShippingService{

    @Value("\${topic.shipping-event.producer}")
    private lateinit var topic : String

    @Transactional
    override fun confirmOrder(orderId: String): String {
        val event =  ShippingEvent("", orderId, OrderStatusEvent.CONFIRMED, "Order confirmed successfully", LocalDateTime.now())
        saveAndPublish(event)
        return OrderStatusEvent.CONFIRMED.description
    }

    @Transactional
    override fun shipOrder(orderId: String): String {
        val event =  ShippingEvent("", orderId, OrderStatusEvent.SHIPPED, "Order Shipped successfully", LocalDateTime.now())
        saveAndPublish(event)
        return OrderStatusEvent.SHIPPED.description
    }

    @Transactional
    override fun deliveryOrder(orderId: String): String {
        val event =  ShippingEvent("", orderId, OrderStatusEvent.SHIPPED, "Order delivered successfully", LocalDateTime.now())
        saveAndPublish(event)
        return OrderStatusEvent.DELIVERED.description
    }

    @Transactional
    override fun cancelOrder(orderId: String): String {
        val event =  ShippingEvent("", orderId, OrderStatusEvent.CANCELED, "Order canceled successfully", LocalDateTime.now())
        saveAndPublish(event)
        return OrderStatusEvent.CANCELED.description
    }

    fun saveAndPublish(event: ShippingEvent){
        shippingEventRepository.save(event)
        kafkaService.sendToKafkaJson(topic, event)
    }
}