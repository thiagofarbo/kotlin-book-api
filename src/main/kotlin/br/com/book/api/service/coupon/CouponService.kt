package br.com.book.api.service.coupon

import br.com.book.api.domain.Coupon

interface CouponService {

    fun verify(code: String): Coupon
}