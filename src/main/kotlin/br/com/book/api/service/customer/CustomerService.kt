package br.com.book.api.service.customer

import br.com.book.api.domain.Customer
import br.com.book.api.repository.CustomerRepository
import org.springframework.stereotype.Service


@Service
class CustomerService(private val customerRepository: CustomerRepository) {

    fun getCustomerByCpf(cpf: String): Customer{
        val customer = customerRepository.findCustomerByCpf(cpf)
        return customer.get();
    }
}