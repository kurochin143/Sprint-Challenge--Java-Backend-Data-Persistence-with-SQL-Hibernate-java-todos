package com.isra.sprint13.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
@Table(name = "tables")
class ToDo {

    @Id
    @GeneratedValue
    var todoid: Long = 0

    @Column(nullable = false)
    var description: String? = null

    var datestarted: String? = null

    var completed: Boolean? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", nullable = false)
    @JsonIgnoreProperties(value = ["todos"])
    var user: User? = null

    constructor()
    constructor(description: String?, datestarted: String?, completed: Boolean?, user: User?) {
        this.description = description
        this.datestarted = datestarted
        this.completed = completed
        this.user = user
    }
}