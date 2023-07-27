package br.com.book.api.domain

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "renter")
data class Renter(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "gen_" + "renter", sequenceName = "sq_" + "renter", allocationSize = 1)
    val id: Long,
    val name: String,
    val email: String,
    val cpf: String,
) : Serializable {
    constructor() : this(0L, "", "", "")

    override fun toString(): String {
        return buildString {
            appendln("Renter(id=$id, name='$name', email='$email', cpf='$cpf')")
        }
    }
}