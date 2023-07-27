package br.com.book.api.repository

import br.com.book.api.domain.Rental
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RentalRepository: CrudRepository<Rental, Long>{

    fun findRentalByRenterId(id: String): Rental

    fun findRentalByCpf(id: String): List<Rental>

    fun findAllByCpf(cpf: String): List<Rental>

}