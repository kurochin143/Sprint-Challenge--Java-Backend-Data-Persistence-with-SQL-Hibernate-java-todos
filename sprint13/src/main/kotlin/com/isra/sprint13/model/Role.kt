package com.isra.sprint13.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
@Table(name = "roles")
class Role : Auditable {

    companion object {
        const val PREFIX = "ROLE_"
        const val ADMIN = "ADMIN"
        const val USER = "USER"
        const val MGR = "MGR"

        const val ROLE_ADMIN = PREFIX+ADMIN
        const val ROLE_USER = PREFIX+USER
        const val ROLE_MGR = PREFIX+MGR
    }

    @Id
    @GeneratedValue
    var roleid: Long = 0

    @Column(nullable = false,
            unique = true)
    var name: String? = null

    @OneToMany(mappedBy = "role",
            cascade = [CascadeType.ALL])
    @JsonIgnoreProperties("role")
    var userroles = mutableListOf<UserRoles>()

    constructor()
    constructor(name: String?) : super() {
        this.name = name
    }

}