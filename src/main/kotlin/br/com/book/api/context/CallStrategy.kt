package br.com.book.api.context

import br.com.book.api.domain.order.OrderRequest
import br.com.book.api.domain.order.OrderResponse
import org.springframework.stereotype.Component

@Component
interface CallStrategy {

    fun createOrder(orderRequest: OrderRequest): OrderResponse
}