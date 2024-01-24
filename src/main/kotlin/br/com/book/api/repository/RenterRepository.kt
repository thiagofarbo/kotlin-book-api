package br.com.book.api.repository

import br.com.book.api.domain.Renter
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RenterRepository: CrudRepository<Renter, Long>{

    fun findRenterByCpf(cpf: String): Optional<Renter>

}