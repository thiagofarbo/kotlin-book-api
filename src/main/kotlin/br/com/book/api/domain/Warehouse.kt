package br.com.book.api.domain

import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "warehouse_books")
data class Warehouse (@Id
                      @GeneratedValue(strategy =  GenerationType.IDENTITY)
                      @SequenceGenerator(name = "gen_" + "book", sequenceName = "sq_" + "warehouse", allocationSize = 1)
    val book_id: Long,
    val in_stock: Boolean,
    val quantity_in_stock: Int,
    val title: String,
    val author: String,
    val isbn: String,
    val publicationYear: Int,
    val publisher: String,
    val numberOfPages: Int,
    val genre: String,
    val language: String,
    val price: BigDecimal,
    val synopsis: String,
    var available: Boolean,
    val created_at: LocalDateTime,
    val updated_at:  LocalDateTime
): Serializable {
    constructor() : this(0L, false,0,"", "","",0,
        "",0,  "", "", BigDecimal.ZERO, "", false, LocalDateTime.now(), LocalDateTime.now()
    )
    override fun toString(): String {
        return "Warehouse(book_id=$book_id, in_stock=$in_stock, quantity_in_stock=$quantity_in_stock, title='$title', author='$author', isbn='$isbn', publicationYear=$publicationYear, publisher='$publisher', numberOfPages=$numberOfPages, genre='$genre', language='$language', price=$price, synopsis='$synopsis', available=$available, created_at=$created_at, updated_at=$updated_at)"
    }
}