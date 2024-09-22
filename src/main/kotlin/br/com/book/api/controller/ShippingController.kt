package br.com.book.api.controller

import br.com.book.api.service.shipment.ShippingService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/shipments")
class ShippingController (private val shippingService: ShippingService){

    @PostMapping("/{orderId}/confirm-orders")
    fun confirm(@PathVariable orderId: String): ResponseEntity<String> {
        return ResponseEntity.ok(shippingService.confirmOrder(orderId))
    }

    @PutMapping("/{orderId}/ship-orders")
    fun ship(@PathVariable orderId: String): ResponseEntity<String> {
        return ResponseEntity.ok(shippingService.shipOrder(orderId))
    }

    @PutMapping("/{orderId}/delivery-orders")
    fun deliveryOrder(@PathVariable orderId: String): ResponseEntity<String> {
        return ResponseEntity.ok(shippingService.deliveryOrder(orderId))
    }


    @PutMapping("/{orderId}/cancel-orders")
    fun cancelOrder(@PathVariable orderId: String): ResponseEntity<String> {
        return ResponseEntity.ok(shippingService.cancelOrder(orderId))
    }
}