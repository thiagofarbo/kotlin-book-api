package br.com.book.api.service.coupon

import br.com.book.api.domain.Coupon
import br.com.book.api.repository.CouponRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class CouponServiceImpl(

    private val couponRepository: CouponRepository

): CouponService  {
    override fun verify(code: String): Coupon {

        val coupon = couponRepository.findByCode(code).orElseThrow { RuntimeException("Voucher not found") }

        return coupon
    }

    override fun calculateOrderPrice(price: BigDecimal, quantity: Int, voucherCode: String?): BigDecimal{

        var orderPrice = price.multiply(quantity.toBigDecimal())

        if (voucherCode != null) {
            val coupon = verify(voucherCode)
            orderPrice -= coupon.discount
        }
        return orderPrice
    }

}