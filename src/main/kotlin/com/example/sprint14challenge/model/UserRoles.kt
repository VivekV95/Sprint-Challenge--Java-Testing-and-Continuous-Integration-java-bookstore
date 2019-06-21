package com.example.sprint14challenge.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

import javax.persistence.*
import java.io.Serializable
import java.util.Objects

@ApiModel(value = "userroles")
@Entity
@Table(name = "userroles")
class UserRoles : Auditable, Serializable {

    @ApiModelProperty(name = "userid", value = "Foreign key for user",
            required = true, example = "1")
    @Id
    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties("userRoles")
    var user: User? = null

    @ApiModelProperty(name = "roleid", value = "Foreign key for role",
            required = true, example = "1")
    @Id
    @ManyToOne
    @JoinColumn(name = "roleid")
    @JsonIgnoreProperties("userRoles")
    var role: Role? = null

    constructor() {}

    constructor(user: User, role: Role) {
        this.user = user
        this.role = role
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o !is UserRoles) {
            return false
        }
        val userRoles = o as UserRoles?
        return user == userRoles!!.user && role == userRoles.role
    }

    override fun hashCode(): Int {
        return Objects.hash(user, role)
    }
}
