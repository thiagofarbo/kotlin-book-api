package br.com.book.api.message

import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import java.util.*


@Slf4j
@Service
@RequiredArgsConstructor
class ConsumerMessages {

    @Value("\${topic.name.consumer}")
    private lateinit var topic : String

    @Value("\${topic.name.groupId}")
    private lateinit var idGroup : String

    @Value("\${topic.name.producer}")
    private lateinit var producer : String

    @KafkaListener(topics = ["\${topic.name.consumer}"], groupId = "\${topic.name.groupId}")
    fun consume(payload: ConsumerRecord<String?, String?>) {
        val configuration = MessageConfig.consumerConfiguration()

        KafkaConsumer<Any?, Any?>(configuration).use { consumer ->
            consumer.subscribe(Arrays.asList(topic))
            System.out.println("key: {}" + payload.key())
            System.out.println("Headers: {}" + payload.headers())
            System.out.println("Partion: {}" + payload.partition())
            System.out.println("Resultado: {}"+ payload.value())
        }
    }
}