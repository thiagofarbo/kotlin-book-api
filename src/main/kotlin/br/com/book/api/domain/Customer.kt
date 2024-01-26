package br.com.book.api.domain

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "customer")
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "gen_" + "customer", sequenceName = "sq_" + "customer", allocationSize = 1)
    val id: Long,
    val name: String,
    val email: String,
    val cpf: String,
) : Serializable {
    constructor() : this(0L, "", "", "")

    override fun toString(): String {
        return buildString {
            appendln("Customer(id=$id, name='$name', email='$email', cpf='$cpf')")
        }
    }
}