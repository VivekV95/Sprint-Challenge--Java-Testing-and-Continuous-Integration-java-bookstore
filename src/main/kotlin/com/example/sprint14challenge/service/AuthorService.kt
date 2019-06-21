package com.example.sprint14challenge.service

import com.example.sprint14challenge.model.Author
import org.springframework.data.domain.Pageable

interface AuthorService {
    fun findAll(pageable: Pageable): MutableList<Author>
}