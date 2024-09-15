package br.com.book.api.domain.enums

enum class OrderStatusEvent(val status: String) {
    CREATED("CREATED"),
    CONFIRMED("CONFIRMED"),
    SHIPPED("SHIPPED"),
    CANCELED("CANCELED"),
    DELIVERED("DELIVERED");

    val description: String = ""
}