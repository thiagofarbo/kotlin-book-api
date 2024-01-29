package br.com.book.api.domain

import org.springframework.data.redis.core.RedisHash
import java.io.Serializable
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "book")
data class Book (@Id
                 @GeneratedValue(strategy =  GenerationType.IDENTITY)
                 @SequenceGenerator(name = "gen_" + "book", sequenceName = "sq_" + "book", allocationSize = 1)
                 val id: Long,
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

//                 @OneToMany(mappedBy = "book")
//                 val renters: List<User> = mutableListOf()
): Serializable {
    constructor() : this(0L, "","","", 0,"",0,
        "","",  BigDecimal.ZERO, "", false
    )

    override fun toString(): String {
        return buildString {
            appendln("Book(title='$title', author='$author', isbn='$isbn', publicationYear=$publicationYear, publisher='$publisher', numberOfPages=$numberOfPages, genre='$genre', language='$language', price=$price, synopsis='$synopsis', available=$available)")
        }
    }
}