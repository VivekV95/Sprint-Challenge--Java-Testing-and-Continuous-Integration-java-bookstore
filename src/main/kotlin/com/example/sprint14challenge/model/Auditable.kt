package com.example.sprint14challenge.model

import io.swagger.annotations.ApiModelProperty
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass
import javax.persistence.Temporal
import java.util.Date

import javax.persistence.TemporalType.TIMESTAMP

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class Auditable {

    @ApiModelProperty(name = "createdBy", value = "What the table item was created by",
            required = false, example = "SYSTEM")
    @CreatedBy
    protected var createdBy: String? = null

    @ApiModelProperty(name = "createdDate", value = "Date and time the table item was created at",
            required = false)
    @CreatedDate
    @Temporal(TIMESTAMP)
    protected var createdDate: Date? = null

    @ApiModelProperty(name = "lastModifiedBy", value = "What the table item was last modified by",
            required = false, example = "SYSTEM")
    @LastModifiedBy
    protected var lastModifiedBy: String? = null

    @ApiModelProperty(name = "lastModifiedDate", value = "When the table item was last modified",
            required = false)
    @LastModifiedDate
    @Temporal(TIMESTAMP)
    protected var lastModifiedDate: Date? = null

}
