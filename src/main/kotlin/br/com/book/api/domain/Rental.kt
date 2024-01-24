package br.com.book.api.domain

import java.io.Serializable
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "rental")
data class Rental (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "gen_" + "rental", sequenceName = "sq_" + "rental", allocationSize = 1)
    val id: Long?,

    @ManyToOne
    @JoinColumn(name = "book_id")
    val book: Book,

    @ManyToOne
    @JoinColumn(name = "renter_id")
    val renter: Renter,

    val rentalDate: LocalDate,

    val returnDate: LocalDate?,

    var status: String,

    val cpf: String
): Serializable {
    constructor() : this(0L, Book(), Renter(), LocalDate.now(), LocalDate.now(), "", ""
    )

    override fun toString(): String {
        return "Rental(id=$id, book=$book, renter=$renter, rentalDate=$rentalDate, returnDate=$returnDate, status='$status', cpf='$cpf')"
    }


}