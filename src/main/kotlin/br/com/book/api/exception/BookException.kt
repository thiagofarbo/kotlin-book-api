package br.com.book.api.exception

class BookException: RuntimeException {

    constructor(mensagem: String) : super(mensagem)

    constructor(mensagem: String, remessa: String) : super(remessa)
}