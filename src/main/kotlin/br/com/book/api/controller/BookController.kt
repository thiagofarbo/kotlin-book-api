package br.com.book.api.controller

import br.com.book.api.domain.Book
import br.com.book.api.service.BookService
import br.com.book.api.util.PageRequestBuilder
import kotlinx.coroutines.runBlocking
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.time.LocalDate

@RestController
@RequestMapping("/v1/books")
class BookController(
    private val bookService: BookService
) {

    @PostMapping
    fun save(@RequestBody book: Book) = bookService.save(book)

    @GetMapping
    fun list(page: Int,
             size: Int,
             isbn: String?,
             title: String?) = bookService.list(isbn, title, PageRequestBuilder.build(page, size))

    @GetMapping("/{isbn}")
    fun findBookByIsbn(@PathVariable isbn: String) = bookService.findBookByIsbn(isbn)

    @GetMapping("/title")
    fun listByTitle(@RequestParam title: String) = bookService.listByTitle(title)

    @GetMapping("/rentals")
    fun listRentals(@RequestParam cpf: String) = bookService.listRentals(cpf)


    @PutMapping("/{id}")
    fun update(@RequestBody book: Book, @PathVariable id: Long) = bookService.update(book, id)

    @GetMapping("/warehouse")
    fun listByInventory(@RequestParam isbn: String) = runBlocking {
        bookService.findBookInWareHouse(isbn)
    }

    @PostMapping("/{isbn}/rents")
    fun rent(@PathVariable isbn: String, @RequestParam cpf:String, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) returnDate:LocalDate): ResponseEntity<Void> {
        bookService.rentBook(isbn, cpf, returnDate)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/returns")
    fun returnBook(@RequestParam title: String, @RequestParam cpf:String, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) returnDate:LocalDate): ResponseEntity<Void> {
        bookService.returnBook(title, cpf, returnDate)
        return ResponseEntity.noContent().build()
    }
}