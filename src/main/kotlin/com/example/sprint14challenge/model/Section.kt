package com.example.sprint14challenge.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
@Table(name = "sections")
class Section (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var sectionid: Long,

        @Column(nullable = false, unique = true)
        var name: String
): Auditable() {

    @OneToMany(mappedBy = "section", cascade = [CascadeType.ALL],
            fetch = FetchType.LAZY)
    @JsonIgnoreProperties("section")
    var books: MutableList<Book> = mutableListOf()
}