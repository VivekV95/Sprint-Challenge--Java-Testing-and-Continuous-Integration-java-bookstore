package com.example.sprint14challenge.service

import com.example.sprint14challenge.exception.ResourceNotFoundException
import com.example.sprint14challenge.model.Author
import com.example.sprint14challenge.repository.AuthorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Component
@Service(value = "authorService")
class AuthorServiceImpl: AuthorService {

    @Autowired
    private lateinit var authorRepo: AuthorRepository

    override fun findAll(pageable: Pageable): MutableList<Author> {
        val list = mutableListOf<Author>()
        authorRepo.findAll(pageable).iterator().forEachRemaining{list.add(it)}
        return list
    }

    override fun findById(authorid: Long): Author {
        return authorRepo.findById(authorid)
                .orElseThrow { ResourceNotFoundException("Author with id $authorid not found") }
    }
}