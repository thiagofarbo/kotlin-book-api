package br.com.book.api.service

import br.com.book.api.domain.Warehouse

interface WarehouseService{

    fun save(wareHouse: Warehouse): Warehouse
    fun findBookByIsbn(isbn: String): Warehouse
    fun findBookByTitle(title: String): Warehouse
}