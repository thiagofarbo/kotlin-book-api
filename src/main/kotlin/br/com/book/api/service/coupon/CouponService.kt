package br.com.book.api.service.coupon

import br.com.book.api.domain.Coupon
import java.math.BigDecimal

interface CouponService {

    fun verify(code: String): Coupon

    fun calculateOrderPrice(price: BigDecimal, quantity: Int, voucherCode: String?): BigDecimal


}