package br.com.book.api.repository

import br.com.book.api.domain.Order
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface OrderRepository: CrudRepository<Order, Long>{

    fun findOrderById(id: String): Order

    fun findOrderByCpf(id: String): List<Order>

    fun findByCpf(cpf: String): Optional<Order>

    fun findAllByCpf(cpf: String): List<Order>

}