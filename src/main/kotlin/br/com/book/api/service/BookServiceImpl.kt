package br.com.book.api.service
import br.com.book.api.domain.Book
import br.com.book.api.domain.Rental
import br.com.book.api.domain.enums.StatusEnum
import br.com.book.api.exception.BookException
import br.com.book.api.repository.RentalRepository
import br.com.book.api.repository.RenterRepository
import br.com.book.api.repository.bookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class BookServiceImpl (

    private val bookRepository: bookRepository,

    private val renterRepository: RenterRepository,

    private val rentalRepository: RentalRepository

): BookService{
    override fun save(book: Book): Book {
        return bookRepository.save(book)
    }

    override fun list(isbn: String?, name: String?, pageRquest: Pageable): Page<Book> {
        return bookRepository.findAllByIsbnOrTitle(isbn, name, pageRquest)
    }

    override fun findBookByIsbn(isbn: String): Book {
        return bookRepository.findBookByIsbn(isbn).orElseThrow { BookException("Book not found.") }
    }

    override fun update(book: Book, id: Long): Book {
        TODO("Not yet implemented")
    }

    override fun listByTitle(title: String): Book {
       return bookRepository.findBookByTitle(title)
    }

    override suspend fun findBookInWareHouse(isbn: String): List<Book> {
        TODO("Not yet implemented")
    }

    override fun rentBook(isbn: String, cpf: String, returnDate: LocalDate) {

        val book = bookRepository.findBookByIsbn(isbn).orElseThrow( { BookException("Book not found.") } )
            book.available = false

        val renter = renterRepository.findRenterByCpf(cpf)

        val rental = Rental(null, book, renter, LocalDate.now(), returnDate, StatusEnum.RENTED.name, cpf)

        rentalRepository.save(rental)
    }

    override fun returnBook(title: String, cpf: String, returnDate: LocalDate) {

        val listRentals = listRentals(cpf)

        val rental = listRentals.stream()
            .filter { r -> r.book.title.equals(title) }
            .findFirst().orElseThrow( { BookException("Rental not found") } )

        rental.status = "RETURNED"
        rentalRepository.save(rental)

        val book = bookRepository.findById(rental.book.id).orElseThrow { BookException("Book not found.")}
        book.available = true
        bookRepository.save(book)
    }

    override fun listRentals(cpf: String): List<Rental> {
        return rentalRepository.findAllByCpf(cpf)
    }
}