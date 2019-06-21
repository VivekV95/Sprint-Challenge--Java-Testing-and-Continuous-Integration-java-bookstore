package com.example.sprint14challenge.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

import javax.persistence.*
import java.util.ArrayList

// User is considered the parent entity
@ApiModel(value = "users")
@Entity
@Table(name = "users")
class User : Auditable {

    @ApiModelProperty(name = "userid", value = "Primary key for users",
            required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var userid: Long = 0

    @ApiModelProperty(name = "username", value = "Unique username of the user",
            required = true, example = "myuser101")
    @Column(nullable = false, unique = true)
    var username: String = ""

    @ApiModelProperty(name = "password", value = "Password of the user",
            required = true, example = "mypassword")
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private var password: String = ""

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    @JsonIgnoreProperties("user")
    var userRoles: List<UserRoles> = ArrayList()

    val authority: List<SimpleGrantedAuthority>
        get() {
            val rtnList = ArrayList<SimpleGrantedAuthority>()

            for (r in this.userRoles) {
                val myRole = "ROLE_" + r.role!!.name!!.toUpperCase()
                rtnList.add(SimpleGrantedAuthority(myRole))
            }

            return rtnList
        }

    constructor() {}

    constructor(name: String, password: String, userRoles: List<UserRoles>) {
        username = name
        setPassword(password)
        for (ur in userRoles) {
            ur.user = this
        }
        this.userRoles = userRoles
    }

    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String) {
        val passwordEncoder = BCryptPasswordEncoder()
        this.password = passwordEncoder.encode(password)
    }

    fun setPasswordNoEncrypt(password: String) {
        this.password = password
    }
}
