package br.com.book.api.service.message

import br.com.book.api.domain.order.OrderResponse
import br.com.book.api.configs.MessageConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaProducerService(val messageConfig : MessageConfig)  {

//    @Value("\${topic.book.producer}")
//    private lateinit var topic : String

    @Autowired
    private lateinit var kafkaTemplate : KafkaTemplate<String, String>
        fun <T> sendToKafkaJson(topic: String, order: T) {
            kafkaTemplate.send(topic, order.toString())
    }
}