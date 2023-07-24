package br.com.book.api.domain

import java.io.Serializable

data class Book (val id: Long,
                 val title: String,
                 val author: String,
                 val isbn: String,
                 val publicationYear: Int,
                 val publisher: String,
                 val numberOfPages: Int,
                 val genre: String,
                 val language: String,
                 val price: Double,
                 val available: Boolean
): Serializable