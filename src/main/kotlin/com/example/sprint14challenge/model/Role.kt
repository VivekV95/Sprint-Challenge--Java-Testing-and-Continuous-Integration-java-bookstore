package com.example.sprint14challenge.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import io.swagger.annotations.ApiModel

import javax.persistence.*
import java.util.ArrayList

@ApiModel
@Entity
@Table(name = "roles")
class Role : Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var roleid: Long = 0

    @Column(nullable = false, unique = true)
    var name: String? = ""

    @OneToMany(mappedBy = "role", cascade = [CascadeType.ALL])
    @JsonIgnoreProperties("role")
    var userRoles: List<UserRoles> = ArrayList()

    constructor() {}

    constructor(name: String) {
        this.name = name
    }
}
