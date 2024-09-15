package br.com.book.api.service.shipment

interface ShippingService {

    fun confirmOrder(orderId: String): String

    fun shipOrder(orderId: String): String

    fun deliveryOrder(orderId: String): String

    fun cancelOrder(orderId: String): String

}