package br.com.book.api.service

import br.com.book.api.domain.payment.PaymentRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient

@Service
class MakePaymentService(){

    @Autowired
    lateinit var restTemplate: RestTemplate

    fun makePayment(request: PaymentRequest){

        val payment = PaymentRequest(request.currency, 1000,request.addressLine1)

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val request = HttpEntity(payment, headers)

        val postForEntity =
            restTemplate.postForEntity("http://localhost:9595/api/v1/payment/square", request, String::class.java)

        println(postForEntity)

    }
}
