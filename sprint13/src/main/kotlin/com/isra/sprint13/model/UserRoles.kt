package com.isra.sprint13.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "userroles")
class UserRoles : Auditable, Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties("userroles")
    var user: User? = null

    @Id
    @ManyToOne
    @JoinColumn(name = "roleid")
    @JsonIgnoreProperties("userroles")
    var role: Role? = null

    constructor()

    constructor(user: User?, role: Role?) : super() {
        this.user = user
        this.role = role
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserRoles

        if (user != other.user) return false
        if (role != other.role) return false

        return true
    }

    override fun hashCode(): Int {
        var result = user?.hashCode() ?: 0
        result = 31 * result + (role?.hashCode() ?: 0)
        return result
    }

}