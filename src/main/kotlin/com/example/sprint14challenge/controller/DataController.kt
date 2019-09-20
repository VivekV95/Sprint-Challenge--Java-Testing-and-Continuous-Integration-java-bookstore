package com.example.sprint14challenge.controller

import com.example.sprint14challenge.model.Author
import com.example.sprint14challenge.model.Book
import com.example.sprint14challenge.model.ErrorDetail
import com.example.sprint14challenge.service.AuthorService
import com.example.sprint14challenge.service.BookService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
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

    @Autowired
    private lateinit var authorService: AuthorService

    @ApiOperation(value = "Updates a Book associated with the bookid"
            , response = Book::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Book updated", response = Book::class),
        ApiResponse(code = 404, message = "Book Not Found", response = ErrorDetail::class)
    ])
    @PreAuthorize("hasAuthority('ROLE_DATA')")
    @PutMapping(value = ["/book/{bookid}"], consumes = ["application/json"],
            produces = ["application/json"])
    fun updateBook(@PathVariable
                   @ApiParam(value = "Book Id", required = true, example = "1")
                   bookid: Long,
                   @ApiParam(value = "Book details to update", required = true)
                   @Valid @RequestBody book: Book): ResponseEntity<*> {
        val book = bookService.updateBook(bookid, book)
        return ResponseEntity(book, HttpStatus.OK)
    }

    @ApiOperation(value = "Updates an Author associated with the authorid"
            , response = Author::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Author updated", response = Author::class),
        ApiResponse(code = 404, message = "Author Not Found", response = ErrorDetail::class)
    ])
    @PreAuthorize("hasAuthority('ROLE_DATA')")
    @PutMapping(value = ["/author/{authorid}"], consumes = ["application/json"],
            produces = ["application/json"])
    fun updateAuthor(@PathVariable
                   @ApiParam(value = "Author Id", required = true, example = "1")
                   authorid: Long,
                   @ApiParam(value = "Author details to update", required = true)
                   @Valid @RequestBody author: Author): ResponseEntity<*> {
        val author = authorService.updateAuthor(authorid, author)
        return ResponseEntity(author, HttpStatus.OK)
    }

    @ApiOperation(value = "Assigns a book with bookid to author with authorid"
            , response = Unit::class)
    @ApiResponses(value = [
        ApiResponse(code = 201, message = "Book updated", response = Book::class),
        ApiResponse(code = 404, message = "Book or Author Not Found", response = ErrorDetail::class)
    ])
    @PreAuthorize("hasAuthority('ROLE_DATA')")
    @PostMapping(value = ["/book/{bookid}/author/{authorid}"])
    fun assignBookToAuthor(@PathVariable
                           @ApiParam(value = "Book Id", required = true, example = "1")
                           bookid: Long,
                           @ApiParam(value = "Author Id", required = true, example = "1")
                           @PathVariable authorid: Long): ResponseEntity<Any> {
        bookService.assignBookToAuthor(bookid, authorid)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @ApiOperation(value = "Deletes a book with bookid"
            , response = Unit::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Book deleted", response = Book::class),
        ApiResponse(code = 404, message = "Book Not Found", response = ErrorDetail::class)
    ])
    @PreAuthorize("hasAuthority('ROLE_DATA')")
    @DeleteMapping(value = ["/book/{bookid}"])
    fun deleteBook(@PathVariable
               @ApiParam(value = "Book Id", required = true, example = "1")
               bookid: Long): ResponseEntity<Any> {
        bookService.delete(bookid)
        return ResponseEntity(HttpStatus.OK)
    }

    @ApiOperation(value = "Deletes an Author with authorid"
            , response = Unit::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Book deleted", response = Book::class),
        ApiResponse(code = 404, message = "Book Not Found", response = ErrorDetail::class)
    ])
    @PreAuthorize("hasAuthority('ROLE_DATA')")
    @DeleteMapping(value = ["/author/{authorid}"])
    fun deleteAuthor(@PathVariable
                   @ApiParam(value = "Author Id", required = true, example = "1")
                   authorid: Long): ResponseEntity<Any> {
        authorService.delete(authorid)
        return ResponseEntity(HttpStatus.OK)
    }

}