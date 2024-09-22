package br.com.book.api.exception

class OrderException: RuntimeException {

    constructor(message: String) : super(message)

    constructor(message: String, bookId: String) : super(bookId)
}