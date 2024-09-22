package br.com.book.api.domain

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "coupon")
data class Coupon (@Id
                 @GeneratedValue(strategy =  GenerationType.IDENTITY)
                 @SequenceGenerator(name = "gen_" + "coupon", sequenceName = "sq_" + "coupon", allocationSize = 1)
                   val id: Long,
                   val code: String,
                   val discount: BigDecimal,
                   val description: String,
                   val expiryDate: LocalDateTime,
                   val active: Boolean,
                   ): Serializable {

    constructor() : this(0L, "", BigDecimal.ZERO, "",LocalDateTime.now(),false)

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
    }
}