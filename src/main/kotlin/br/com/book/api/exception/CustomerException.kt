package br.com.book.api.exception

class CustomerException: RuntimeException {

    constructor(message: String) : super(message)

    constructor(message: String, cpf: String) : super(cpf)
}