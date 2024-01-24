package br.com.book.api.service

import br.com.book.api.domain.Book
import br.com.book.api.domain.Rental
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.time.LocalDate

interface BookService{

    fun save(book: Book): Book

    fun list(isbn: String?, title: String?, pageRquest: Pageable): Page<Book>

    fun findBookByIsbn(isbn: String): Book

    fun update(book: Book, id: Long): Book

    fun listByTitle(title: String): Book

    suspend fun findBookInWareHouse(isbn: String): List<Book>

    fun rentBook(isbn: String, cpf:String, returnDate: LocalDate): Rental

    fun returnBook(title: String, cpf:String, returnDate: LocalDate)

    fun listRentals(cpf: String): List<Rental>

}