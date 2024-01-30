package br.com.book.api.service
import br.com.book.api.domain.Warehouse
import br.com.book.api.exception.BookException
import br.com.book.api.repository.WarehouseRepository
import org.springframework.stereotype.Service

@Service
class WarehouseServiceImpl (

    private val warehouseRepository: WarehouseRepository,

): WarehouseService{

    override fun save(warehouse: Warehouse): Warehouse {
        return warehouseRepository.save(warehouse)
    }

    override fun findBookByIsbn(isbn: String): Warehouse {
        return warehouseRepository.findWarehouseByIsbn(isbn).orElseThrow( { BookException("Book not found: ", isbn) } )
    }

    override fun findBookByTitle(title: String): Warehouse {
        return warehouseRepository.findWarehouseByTitle(title).orElseThrow( { BookException("Book not found :", title) } )
    }
}