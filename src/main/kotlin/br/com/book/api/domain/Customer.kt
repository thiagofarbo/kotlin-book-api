package br.com.book.api.domain

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import org.springframework.data.redis.core.RedisHash
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
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Customer) return false

        if (id != other.id) return false
        if (name != other.name) return false
        if (email != other.email) return false
        if (cpf != other.cpf) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + cpf.hashCode()
        return result
    }
}