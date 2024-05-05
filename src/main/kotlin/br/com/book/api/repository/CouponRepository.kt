package br.com.book.api.repository

import br.com.book.api.domain.Coupon
import org.springframework.data.repository.CrudRepository
import java.util.*

interface CouponRepository : CrudRepository<Coupon, Long>{

    fun findByCode(code: String): Optional<Coupon>
}