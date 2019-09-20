package com.example.sprint14challenge.repository

import com.example.sprint14challenge.model.Book
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository

interface BookRepository: PagingAndSortingRepository<Book, Long> {

    @Modifying
    @Query("INSERT INTO wrote (bookid, authorid) VALUES (:bookid, :authorid)", nativeQuery = true)
    fun assignBookToAuthor(bookid: Long, authorid: Long)
}