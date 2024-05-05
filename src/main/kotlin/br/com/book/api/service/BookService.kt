package br.com.book.api.service

import br.com.book.api.domain.Book
import br.com.book.api.domain.Order
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.math.BigDecimal
import java.time.LocalDate

interface BookService{

    fun save(book: Book): Book

    fun list(isbn: String?, title: String?, pageRquest: Pageable): Page<Book>

    fun findBookByIsbn(isbn: String): Book

    fun update(book: Book, id: Long): Book

     fun listByTitle(title: String): Book

     fun findBookInWareHouse(isbn: String): List<Book>

     fun rentBook(isbn: String, cpf:String, returnDate: LocalDate, price: BigDecimal): Order

     fun returnBook(title: String, cpf:String, returnDate: LocalDate)

     fun listOrders(cpf: String): List<Order>

     fun purchase(isbn: String, cpf:String, quantity: Int, orderPrice: BigDecimal): Order

}