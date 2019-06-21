package com.example.sprint14challenge.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
@Table(name = "authors")
class Author(@Id
             @GeneratedValue(strategy = GenerationType.AUTO)
             var authorid: Long = 0,

             @Column(nullable = false)
             var fname: String = "",

             @Column(nullable = false)
             var lname: String = "") : Auditable() {

    @ManyToMany(mappedBy = "authors")
    @JsonIgnoreProperties("authors")
    var books: MutableList<Book> = mutableListOf()

}