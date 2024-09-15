package br.com.book.api.service.message

import br.com.book.api.configs.MessageConfig
import br.com.book.api.domain.Order
import br.com.book.api.domain.order.ShippingEvent
import br.com.book.api.service.BookService
import br.com.book.api.service.EmailService
import br.com.book.api.service.OrderService
import br.com.book.api.service.customer.CustomerService
import br.com.book.api.util.JsonUtil
import kotlinx.coroutines.runBlocking
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import java.util.*


@Slf4j
@Service
@RequiredArgsConstructor
class ShipmentEventConsumerService (private val emailService: EmailService,
                                    private val customerService: CustomerService,
                                    private val orderService: OrderService,
                                    private val bookService: BookService
){

    companion object {
        val logger = LoggerFactory.getLogger(ShipmentEventConsumerService::class.java)
    }

    @Value("\${topic.shipping-event.consumer}")
    private lateinit var topic : String

    val jsonUtil = JsonUtil()

    @KafkaListener(topics = ["\${topic.shipping-event.consumer}"], groupId = "\${topic.shipping-event.groupId}")
    fun consume(payload: ConsumerRecord<String?, String?>) = runBlocking{
        val configuration = MessageConfig.consumerConfiguration()

        KafkaConsumer<Any?, Any?>(configuration).use { consumer ->
            consumer.subscribe(Arrays.asList(topic))
            logger.info("key: {} " , payload.key())
            logger.info("Headers: {} ", payload.headers())
            logger.info("Partition: {} ", payload.partition())
            logger.info("Shipment Response:  {} ", payload.value())
            val shippingEvent = jsonUtil.toShipmentObject<ShippingEvent>(payload.value().toString(), ShippingEvent::class.java)
            sendEmail(shippingEvent)
        }
    }

     suspend fun sendEmail(shippingEvent : ShippingEvent) {

         val order = orderService.getOrderById(shippingEvent.orderId)

         val book = bookService.findBookById(order.book.id)

         val customer = customerService.getCustomerByCpf(order.cpf)

         val variables = mapOf("name" to customer.name, "bookName" to book.title)
         emailService.send(customer.email, "Status Entrega", "email-template", variables)
    }
}