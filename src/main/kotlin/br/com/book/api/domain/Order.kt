package br.com.book.api.domain

import br.com.book.api.domain.enums.OrderTypeEnum
import java.io.Serializable
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "orders")
data class Order (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "gen_" + "order", sequenceName = "sq_" + "order", allocationSize = 1)
    val id: String?,

    @ManyToOne
    @JoinColumn(name = "book_id")
    val book: Book,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    val customer: Customer,

    val orderDate: LocalDate,

    val returnDate: LocalDate?,

    var status: String,

    val cpf: String,

    var orderType: String,

    var quantity: Int?,

    ): Serializable {
    constructor() : this("", Book(), Customer(), LocalDate.now(), LocalDate.now(), "", "", OrderTypeEnum.PURCHASE.name, 0
    )

    override fun toString(): String {
        return "Order(id=$id, book=$book, customer=$customer, orderDate=$orderDate, returnDate=$returnDate, status='$status', cpf='$cpf', orderType=$orderType, quantity=$quantity)"
    }


}