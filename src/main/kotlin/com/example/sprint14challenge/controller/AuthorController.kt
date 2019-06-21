package com.example.sprint14challenge.controller

import com.example.sprint14challenge.service.AuthorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/authors")
class AuthorController {

    @Autowired
    private lateinit var authorService: AuthorService

    @PreAuthorize("hasAuthority('ROLE_USER')")
    fun findAll(pageable: Pageable): ResponseEntity<*> {
        val authors = authorService.findAll(pageable).isEmpty()
        return ResponseEntity(authors, HttpStatus.OK)
    }
}