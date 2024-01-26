package br.com.book.api.repository

import br.com.book.api.domain.Customer
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CustomerRepository: CrudRepository<Customer, Long>{

    fun findCustomerByCpf(cpf: String): Optional<Customer>

}