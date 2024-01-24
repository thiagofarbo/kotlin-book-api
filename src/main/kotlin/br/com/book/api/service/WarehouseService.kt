package br.com.book.api.service

import br.com.book.api.domain.Warehouse

interface WarehouseService{

    fun save(isbn: Warehouse): Warehouse
    fun findBookByIsbn(isbn: String): Warehouse
    fun findBookByTitle(tile: String): Warehouse
}