package br.com.book.api.service.orderNumber

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Component
import org.springframework.web.servlet.function.ServerResponse.async
import java.util.*

@Component
class GenerateOrder{
    suspend fun generate(): String{
        val currentLocale: Locale = Locale.getDefault()
        val countryCode: String = currentLocale.country
        val order = generateSequence(1) { it + 1 }
            .take(1)
            .distinct()
            .sorted()
            .toSet()

        val number = order.stream().findFirst().get()
        val orderNumber = countryCode + number
        print("Order number :  $orderNumber")
        return orderNumber
    }
}