package br.com.book.api.domain.payment

import lombok.Builder

@Builder
data class PaymentResponse (val message: String)