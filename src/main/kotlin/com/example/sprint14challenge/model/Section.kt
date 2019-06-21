package com.example.sprint14challenge.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.persistence.*

@ApiModel(value = "sections")
@Entity
@Table(name = "sections")
class Section (
        @ApiModelProperty(name = "sectionid", value = "Primary key for sections",
                required = true, example = "1")
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var sectionid: Long,

        @ApiModelProperty(name = "name", value = "Name of the section",
                required = true, example = "Fiction")
        @Column(nullable = false, unique = true)
        var name: String
): Auditable() {

    @OneToMany(mappedBy = "section", cascade = [CascadeType.ALL],
            fetch = FetchType.LAZY)
    @JsonIgnoreProperties("section")
    var books: MutableList<Book> = mutableListOf()
}