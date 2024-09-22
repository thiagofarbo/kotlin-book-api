package br.com.book.api.service

import br.com.book.api.domain.payment.PaymentRequest
import br.com.book.api.domain.payment.PaymentResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class MakePaymentService(){

    @Autowired
    lateinit var restTemplate: RestTemplate

    fun makePayment(request: PaymentRequest): PaymentResponse {

        val payment = PaymentRequest(request.currency, 1000, request.addressLine1)

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val postForEntity =
            restTemplate.postForEntity("http://localhost:8081/api/v1/payment/square", HttpEntity(payment, headers), String::class.java)
        println(postForEntity)

        var paymentResponse = PaymentResponse()
        if (postForEntity.statusCode.value() == HttpStatus.OK.value()) {
            paymentResponse.statusCode = postForEntity.statusCode.value()
        } else {
            println("Error to request payment service: ${postForEntity.statusCode}")
        }
        return paymentResponse
    }
}
