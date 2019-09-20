package com.example.sprint14challenge.repository

import com.example.sprint14challenge.model.Author
import org.springframework.data.repository.PagingAndSortingRepository

interface AuthorRepository: PagingAndSortingRepository<Author, Long>