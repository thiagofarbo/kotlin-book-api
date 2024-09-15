package br.com.book.api.repository

import br.com.book.api.domain.order.ShippingEvent
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ShippingEventRepository: CrudRepository<ShippingEvent, String> {
}