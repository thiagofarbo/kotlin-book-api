package br.com.book.api.exception

class CustomerException: RuntimeException {

    constructor(mensagem: String) : super(mensagem)

    constructor(mensagem: String, cpf: String) : super(cpf)
}