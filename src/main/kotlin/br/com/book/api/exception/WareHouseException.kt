package br.com.book.api.exception

class WareHouseException: RuntimeException {

    constructor(message: String) : super(message)

    constructor(message: String, quantity: Int) : super(message)
}