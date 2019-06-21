package com.example.sprint14challenge.controller

import com.example.sprint14challenge.exception.ResourceNotFoundException
import com.example.sprint14challenge.model.Book
import com.example.sprint14challenge.service.BookService
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/books")
class BookController {

    @Autowired
    private lateinit var bookService: BookService

    @ApiOperation(value = "Return all Books", response = Book::class, responseContainer = "List")
    @ApiImplicitParams(
            ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    )
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping(value = ["/"], produces = ["application/json"])
    fun findAll(pageable: Pageable): ResponseEntity<*> {
        val books = bookService.findAll(pageable)
        if (books.isEmpty()) throw ResourceNotFoundException("No books were found")
        return ResponseEntity(books, HttpStatus.OK)
    }
}