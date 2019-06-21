package com.example.sprint14challenge.service

import com.example.sprint14challenge.model.Author
import com.example.sprint14challenge.model.Book
import org.springframework.data.domain.Pageable

interface AuthorService {
    fun findAll(pageable: Pageable): MutableList<Author>

    fun findById(authorid: Long): Author
}