package br.com.book.api.controller

import br.com.book.api.context.CallStrategy
import br.com.book.api.domain.Book
import br.com.book.api.domain.enums.OrderTypeEnum
import br.com.book.api.domain.order.OrderRequest
import br.com.book.api.domain.order.OrderResponse
import br.com.book.api.service.BookService
import br.com.book.api.util.PageRequestBuilder
import kotlinx.coroutines.runBlocking
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal
import java.time.LocalDate

@RestController
@RequestMapping("/v1/api/books")
class BookController(

    private val callService: CallStrategy,

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

    @GetMapping("/orders")
    fun listOrders(@RequestParam cpf: String) = bookService.listOrders(cpf)


    @PutMapping("/{id}")
    fun update(@RequestBody book: Book, @PathVariable id: Long) = bookService.update(book, id)

    @GetMapping("/warehouse")
    fun listByInventory(@RequestParam isbn: String) = runBlocking {
        bookService.findBookInWareHouse(isbn)
    }

    @PostMapping("/{isbn}/rents")
    fun rent(@PathVariable isbn: String, @RequestParam cpf:String,@RequestParam quantity: Int, @RequestParam voucherCode: String, @RequestParam price: BigDecimal, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) returnDate:LocalDate?, @RequestParam isVirtualBook: Boolean): ResponseEntity<OrderResponse> {
        val orderRequest = OrderRequest(isbn, cpf, OrderTypeEnum.RENT, quantity, returnDate, voucherCode, price,isVirtualBook)
        return ResponseEntity.ok(callService.createOrder(orderRequest))
    }

    @PostMapping("/returns")
    fun returnBook(@RequestParam title: String, @RequestParam cpf:String, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) returnDate:LocalDate): ResponseEntity<Void> {
        bookService.returnBook(title, cpf, returnDate)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/{isbn}/purchase")
        fun purchase(@PathVariable isbn: String, @RequestParam cpf:String, @RequestParam quantity: Int, @RequestParam voucherCode: String, @RequestParam price: BigDecimal, @RequestParam isVirtualBook: Boolean): ResponseEntity<OrderResponse> {
        val orderRequest = OrderRequest(isbn, cpf, OrderTypeEnum.PURCHASE, quantity, null, voucherCode, price, isVirtualBook)
        return ResponseEntity.ok(callService.createOrder(orderRequest))
    }
}