package br.com.book.api.repository

import br.com.book.api.domain.Warehouse
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface WarehouseRepository: CrudRepository<Warehouse, Long>{

    fun findWarehouseByIsbn(isbn:String): Optional<Warehouse>

    fun findWarehouseByTitle(title:String): Optional<Warehouse>
}