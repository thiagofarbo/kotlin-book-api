package br.com.book.api.domain

import lombok.Getter
import lombok.Setter
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Getter
@Setter
@Table(name = "warehouse_books")
data class Warehouse (@Id
                      @GeneratedValue(strategy =  GenerationType.IDENTITY)
                      @SequenceGenerator(name = "gen_" + "book", sequenceName = "sq_" + "warehouse", allocationSize = 1)
    val bookId: Long,
    val inStock: Boolean,
    var quantityInStock: Int,
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
    val createdAt: LocalDateTime,
    val updatedAt:  LocalDateTime
): Serializable {
    constructor() : this(0L, false,0,"", "","",0,
        "",0,  "", "", BigDecimal.ZERO, "", false, LocalDateTime.now(), LocalDateTime.now()
    )
    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
    }
}