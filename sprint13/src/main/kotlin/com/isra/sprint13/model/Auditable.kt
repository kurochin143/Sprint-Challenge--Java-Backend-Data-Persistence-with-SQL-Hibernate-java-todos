package com.isra.sprint13.model

import org.springframework.data.jpa.domain.support.AuditingEntityListener
import javax.persistence.MappedSuperclass
import javax.persistence.Temporal
import javax.persistence.TemporalType

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.util.*

import javax.persistence.EntityListeners

@MappedSuperclass
@EntityListeners(value = [AuditingEntityListener::class])
abstract class Auditable {

    @CreatedBy
    var createdBy: String? = null

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    var createdDate: Date? = null

    @LastModifiedBy
    var lastModifiedBy: String? = null

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    var lastModifiedDate: Date? = null

}