package com.example.sprint14challenge.controller

import com.example.sprint14challenge.model.Book
import com.example.sprint14challenge.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/data")
class DataController {

    @Autowired
    private lateinit var bookService: BookService


    @PreAuthorize("hasAuthority('ROLE_DATA')")
    @PutMapping(value = ["/book/{bookid}"], consumes = ["application/json"],
            produces = ["application/json"])
    fun updateBook(@PathVariable bookid: Long,
                   @Valid @RequestBody book: Book): ResponseEntity<*> {
        val book = bookService.updateBook(bookid, book)
        return ResponseEntity(book, HttpStatus.OK)
    }

    @PreAuthorize("hasAuthority('ROLE_DATA')")
    @PostMapping(value = ["/book/{bookid}/author/{authorid}"])
    fun assignBookToAuthor(@PathVariable bookid: Long,
                           @PathVariable authorid: Long): ResponseEntity<Any> {
        bookService.assignBookToAuthor(bookid, authorid)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PreAuthorize("hasAuthority('ROLE_DATA')")
    @DeleteMapping(value = ["/book/{bookid}"])
    fun delete(@PathVariable bookid: Long): ResponseEntity<Any> {
        bookService.delete(bookid)
        return ResponseEntity(HttpStatus.OK)
    }
}