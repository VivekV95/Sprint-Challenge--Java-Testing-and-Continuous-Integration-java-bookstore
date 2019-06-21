package com.example.sprint14challenge.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
@Table(name = "books")
class Book(@Id
           @GeneratedValue(strategy = GenerationType.AUTO)
           var bookid: Long = 0,

           @Column(nullable = false)
           var title: String = "",

           @Column(nullable = false, unique = true)
           var ISBN: String = "",

           var copy: Int? = 0): Auditable() {

    @ManyToMany
    @JoinTable(name = "wrote",
            joinColumns = [JoinColumn(name = "bookid")],
            inverseJoinColumns = [JoinColumn(name = "authorid")])
    @JsonIgnoreProperties("books")
    var authors: MutableList<Author> = mutableListOf()

    @ManyToOne
    @JoinColumn(name = "sectionid")
    @JsonIgnoreProperties("books")
    var section: Section? = null
}