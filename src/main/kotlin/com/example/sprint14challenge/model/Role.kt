package com.example.sprint14challenge.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

import javax.persistence.*
import java.util.ArrayList

@ApiModel(value = "roles")
@Entity
@Table(name = "roles")
class Role : Auditable {
    @ApiModelProperty(name = "roleid", value = "Primary key for roles",
            required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var roleid: Long = 0

    @ApiModelProperty(name = "name", value = "Name of the role",
            required = true, example = "admin")
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
