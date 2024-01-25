package br.com.book.api.exception

class RentalException: RuntimeException {

    constructor(mensagem: String) : super(mensagem)

    constructor(mensagem: String, bookId: String) : super(bookId)
}