package br.com.book.api.repository

import br.com.book.api.domain.Book
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import org.springframework.data.repository.CrudRepository
import java.util.Optional

@Repository
interface BookRepository: CrudRepository<Book, Long>{

    fun findBookByIsbn(isbn:String): Optional<Book>

    fun findBookByTitle(title:String): Book

    fun findAllByIsbnOrTitle(isbn: String?, title: String?, pageable: Pageable): Page<Book>
}