package com.isra.sprint13.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.*

@Entity
@Table(name = "users")
class User : Auditable {

    @Id
    @GeneratedValue
    var userid: Long = 0

    @Column(nullable = false,
            unique = true)
    var username: String? = null

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var password: String? = null

    @OneToMany(mappedBy = "user",
            cascade = [CascadeType.ALL])
    @JsonIgnoreProperties("user")
    var userroles: List<UserRoles> = mutableListOf()

    @OneToMany(mappedBy = "user",
            cascade = [CascadeType.ALL])
    @JsonIgnoreProperties("user")
    var todos: List<Todo> = mutableListOf()

    constructor()

    constructor(username: String?, password: String?, userRoles: List<UserRoles>, todos: List<Todo>) : super() {
        this.username = username
        setPasswordEncrypt(password)
        this.userroles = userRoles
        this.todos = todos

        userRoles.forEach {
            it.user = this
        }
    }

    fun setPasswordEncrypt(password: String?) {
        val encoder = BCryptPasswordEncoder()
        this.password = encoder.encode(password)
    }

    fun getAuthority(): List<SimpleGrantedAuthority> {
        val outList = mutableListOf<SimpleGrantedAuthority>()

        userroles.forEach {
            val myRole = Role.PREFIX + it.role?.name?.toUpperCase()
            outList.add(SimpleGrantedAuthority(myRole))
        }

        return outList
    }
}