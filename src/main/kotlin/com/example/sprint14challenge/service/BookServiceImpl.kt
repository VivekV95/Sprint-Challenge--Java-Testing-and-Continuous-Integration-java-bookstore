package com.example.sprint14challenge.service

import com.example.sprint14challenge.exception.ResourceNotFoundException
import com.example.sprint14challenge.model.Book
import com.example.sprint14challenge.repository.AuthorRepository
import com.example.sprint14challenge.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Component
@Service(value = "bookService")
class BookServiceImpl: BookService {

    @Autowired
    private lateinit var bookRepo: BookRepository

    @Autowired
    private lateinit var authorRepo: AuthorRepository

    override fun findAll(pageable: Pageable): MutableList<Book> {
        val list = mutableListOf<Book>()
        bookRepo.findAll(pageable).iterator().forEachRemaining{list.add(it)}
        return list
    }

    @Transactional
    override fun updateBook(bookid: Long, book: Book): Book {
        val newBook = bookRepo.findById(bookid)
                .orElseThrow { ResourceNotFoundException("Book with id $bookid not found") }
        if (book.ISBN != "")  newBook.ISBN = book.ISBN
        if (book.copy != 0) newBook.copy = book.copy
        if (book.title != "") newBook.title = book.title
        if (book.section != null) newBook.section = book.section

        for (author in book.authors) {
            newBook.authors.add(author)
            author.books.add(newBook)
        }
        return bookRepo.save(newBook)
    }

    @Transactional
    override fun assignBookToAuthor(bookid: Long, authorid: Long) {
        if (bookRepo.findById(bookid).isPresent && authorRepo.findById(authorid).isPresent) {
            bookRepo.assignBookToAuthor(bookid, authorid)
        } else {
            throw ResourceNotFoundException("Couldn't find book or author with given id")
        }
    }

    @Transactional
    override fun delete(bookid: Long) {
        if (bookRepo.findById(bookid).isPresent) {
            bookRepo.deleteById(bookid)
        } else {
            throw ResourceNotFoundException("Book with id $bookid not found")
        }
    }
}