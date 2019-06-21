package com.example.sprint14challenge.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.persistence.*

@ApiModel(value = "authors")
@Entity
@Table(name = "authors")
class Author(
        @ApiModelProperty(name = "authorid", value = "Primary key for authors",
                required = true, example = "1")
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var authorid: Long = 0,

        @ApiModelProperty(name = "fname", value = "Author's first name",
                required = true, example = "John")
        @Column(nullable = false)
        var fname: String = "",

        @ApiModelProperty(name = "lname", value = "Author's last name",
                required = true, example = "Mitchell")
        @Column(nullable = false)
        var lname: String = "") : Auditable() {

    @ManyToMany(mappedBy = "authors")
    @JsonIgnoreProperties("authors")
    var books: MutableList<Book> = mutableListOf()

}