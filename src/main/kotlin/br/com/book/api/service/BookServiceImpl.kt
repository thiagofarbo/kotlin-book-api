package br.com.book.api.service

import br.com.book.api.domain.Book
import br.com.book.api.domain.Order
import br.com.book.api.domain.Warehouse
import br.com.book.api.domain.enums.OrderTypeEnum
import br.com.book.api.domain.enums.StatusEnum
import br.com.book.api.domain.payment.PaymentRequest
import br.com.book.api.exception.BookException
import br.com.book.api.exception.OrderException
import br.com.book.api.exception.WareHouseException
import br.com.book.api.repository.BookRepository
import br.com.book.api.repository.CustomerRepository
import br.com.book.api.repository.OrderRepository
import br.com.book.api.repository.RedisRepository
import br.com.book.api.service.message.KafkaProducerService
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.util.*


@Service
class BookServiceImpl (

    private val bookRepository: BookRepository,

    private val customerRepository: CustomerRepository,

    private val orderRepository: OrderRepository,

    private val orderService: OrderService,

    private val redisRepository: RedisRepository,

    private val  paymentService: MakePaymentService,

    private val warehouseService: WarehouseService

): BookService{

    override fun save(book: Book): Book {
        return bookRepository.save(book)
    }

    override fun list(isbn: String?, title: String?, pageRquest: Pageable): Page<Book> {
        return bookRepository.findAllByIsbnOrTitle(isbn, title, pageRquest)
    }

    override fun findBookByIsbn(isbn: String): Book {
        return bookRepository.findBookByIsbn(isbn).orElseThrow { BookException("Book not found.") }
    }

    override fun findBookById(id: Long): Book {
        return bookRepository.findById(id).get()
    }

    override fun update(book: Book, id: Long): Book {
        TODO("Not yet implemented")
    }

    override fun listByTitle(title: String): Book {
       return bookRepository.findBookByTitle(title)
    }

    override  fun findBookInWareHouse(isbn: String): List<Book> {
        TODO("Not yet implemented")
    }

    override fun rentBook(isbn: String, cpf: String, quantity: Int, returnDate: LocalDate, price: BigDecimal): Order {

        val book = bookRepository.findBookByIsbn(isbn).orElseThrow( { BookException("Book not found.") } )
            book.available = false

        val isOrdered = orderRepository.findAllByCpf(cpf).any { order -> order.book.id == book.id }

        if(isOrdered){
            throw OrderException("Book already rented", book.id.toString())
        }

        var customer = redisRepository.findByCpf(cpf)
        if (!customer.isPresent){
            customer =  Optional.of(customerRepository.findCustomerByCpf(cpf).orElseThrow({ BookException("User not found.") }))
        }
        val order = Order(orderService.getOrderNumber(), book, customer.get(), LocalDate.now(), returnDate, StatusEnum.RENTED.name, cpf, OrderTypeEnum.RENT.name, quantity, price)

        val paymentRequest = PaymentRequest("USD", price.longValueExact(), "AV TEST" )

        paymentService.makePayment(paymentRequest)

        val rentedBook = orderRepository.save(order)
        redisRepository.save(customer.get())

        return rentedBook
    }

    override fun returnBook(title: String, cpf: String, returnDate: LocalDate) {

        val listOrders = listOrders(cpf)

        val order = listOrders.stream()
            .filter { r -> r.book.title.equals(title) }
            .findFirst().orElseThrow( { BookException("Order not found") } )

        order.status = StatusEnum.RETURNED.name
        orderRepository.save(order)

        val book = bookRepository.findById(order.book.id).orElseThrow { BookException("Book not found.")}
        book.available = true
        bookRepository.save(book)
    }

    override fun listOrders(cpf: String): List<Order> {
        return orderRepository.findAllByCpf(cpf)
    }

    override fun purchase(isbn: String, cpf: String, quantity: Int, orderPrice: BigDecimal, isVirtualBook: Boolean): Order {

        lateinit var orderDone: Order

        val customer = customerRepository.findCustomerByCpf(cpf).orElseThrow { BookException("User not found.") }

        val wareHouseBook = warehouseService.findBookByIsbn(isbn)
        validateQuantityToBePurchased(wareHouseBook, quantity)

        val book = bookRepository.findBookByIsbn(isbn).orElseThrow { BookException("Book not found.") }

        val order = Order(orderService.getOrderNumber(), book, customer, LocalDate.now(), null, null, cpf, OrderTypeEnum.PURCHASE.name, quantity, orderPrice)

        val price = book.price.setScale(0, RoundingMode.HALF_UP)
        val paymentRequest = PaymentRequest("USD", price.longValueExact(), "AV TEST" )
        val paymentResponse = paymentService.makePayment(paymentRequest)

        if (paymentResponse.statusCode == HttpStatus.OK.value()) {
            orderDone = orderRepository.save(order)
        }
        return orderDone
    }

    fun validateQuantityToBePurchased(wareHouseBook: Warehouse, quantity: Int){
        if (wareHouseBook.quantityInStock < quantity){
            throw WareHouseException("The quantity to be purchased is greater than what we have in stock.", quantity)
        }
    }
}