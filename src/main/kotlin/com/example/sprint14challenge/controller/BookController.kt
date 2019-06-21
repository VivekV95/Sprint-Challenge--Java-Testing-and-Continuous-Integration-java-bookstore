package com.example.sprint14challenge.controller

import com.example.sprint14challenge.service.BookService
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

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping(value = ["/"], produces = ["application/json"])
    fun findAll(pageable: Pageable): ResponseEntity<*> {
        val books = bookService.findAll(pageable)
        return ResponseEntity(books, HttpStatus.OK)
    }
}