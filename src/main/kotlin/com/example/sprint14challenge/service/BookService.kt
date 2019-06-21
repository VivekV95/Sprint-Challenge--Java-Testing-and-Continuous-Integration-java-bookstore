package com.example.sprint14challenge.service

import com.example.sprint14challenge.model.Book
import org.springframework.data.domain.Pageable

interface BookService {

    fun findAll(pageable: Pageable): MutableList<Book>

    fun findById(bookid: Long): Book

    fun updateBook(bookid: Long, book: Book): Book

    fun assignBookToAuthor(bookid: Long, authorid: Long)

    fun delete(bookid: Long)
}