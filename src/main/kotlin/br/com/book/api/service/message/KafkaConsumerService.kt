package br.com.book.api.service.message

import br.com.book.api.configs.MessageConfig
import br.com.book.api.domain.order.OrderResponse
import br.com.book.api.service.WarehouseService
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
class KafkaConsumerService ( private val warehouseService: WarehouseService){

    companion object {
        val logger = LoggerFactory.getLogger(KafkaConsumerService::class.java)
    }

    @Value("\${topic.name.consumer}")
    private lateinit var topic : String

    @Value("\${topic.name.groupId}")
    private lateinit var idGroup : String

    @Value("\${topic.name.producer}")
    private lateinit var producer : String

    val jsonUtil = JsonUtil()

    @KafkaListener(topics = ["\${topic.name.consumer}"], groupId = "\${topic.name.groupId}")
    fun consume(payload: ConsumerRecord<String?, String?>) = runBlocking{
        val configuration = MessageConfig.consumerConfiguration()

        KafkaConsumer<Any?, Any?>(configuration).use { consumer ->
            consumer.subscribe(Arrays.asList(topic))
            logger.info("key: {} " , payload.key())
            logger.info("Headers: {} ", payload.headers())
            logger.info("Partition: {} ", payload.partition())
            logger.info("Result:  {} ", payload.value())
            val order = jsonUtil.toObject<OrderResponse>(payload.value().toString(), OrderResponse::class.java)
            updateStock(order)
        }
    }

     suspend fun updateStock(order : OrderResponse) {
        val warehouseValue = warehouseService.findBookByIsbn(order.book.isbn)
        warehouseValue.quantity_in_stock -= order.quantity!!
        warehouseService.save(warehouseValue)
    }
}