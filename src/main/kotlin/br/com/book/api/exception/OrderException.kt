package br.com.book.api.exception

class OrderException: RuntimeException {

    constructor(mensagem: String) : super(mensagem)

    constructor(mensagem: String, bookId: String) : super(bookId)
}