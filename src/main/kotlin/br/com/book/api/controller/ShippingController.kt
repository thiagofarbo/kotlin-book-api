package br.com.book.api.controller

import br.com.book.api.service.shipment.ShippingService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/ship")
class ShippingController (private val shippingService: ShippingService){

    @PostMapping("/{orderId}/ship")
    fun rent(@PathVariable orderId: String): ResponseEntity<String> {
        return ResponseEntity.ok(shippingService.shipOrder(orderId))
    }
}