package com.example.sprint14challenge.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.persistence.*

@ApiModel(value = "books")
@Entity
@Table(name = "books")
class Book(
        @ApiModelProperty(name = "bookid", value = "Primary key for books",
                required = true, example = "1")
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var bookid: Long = 0,

        @ApiModelProperty(name = "title", value = "Title of book",
                required = true, example = "Harry Potter")
        @Column(nullable = false)
        var title: String = "",

        @ApiModelProperty(name = "ISBN", value = "Book's ISBN number",
                required = true, example = "9780738206752")
        @Column(nullable = false, unique = true)
        var ISBN: String = "",

        @ApiModelProperty(name = "copy", value = "Year the book was published",
                required = false, example = "2007")
        var copy: Int? = 0) : Auditable() {

    @ManyToMany
    @JoinTable(name = "wrote",
            joinColumns = [JoinColumn(name = "bookid")],
            inverseJoinColumns = [JoinColumn(name = "authorid")])
    @JsonIgnoreProperties("books")
    var authors: MutableList<Author> = mutableListOf()

    @ApiModelProperty(name = "sectionid", value = "Foreign key for book's section",
            required = false, example = "3")
    @ManyToOne
    @JoinColumn(name = "sectionid")
    @JsonIgnoreProperties("books")
    var section: Section? = null
}