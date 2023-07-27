package br.com.book.api.util

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

class PageRequestBuilder {

    companion object {
        fun build(
            page: Int,
            size: Int,
//            sort: Sort,
//            direction: Sort.Direction
        ): Pageable {
            val one = 1
            return PageRequest.of(
                    page,
                    size,
//                    Sort.Direction.valueOf(direction.name),
//                    sort
                )
        }
    }

}