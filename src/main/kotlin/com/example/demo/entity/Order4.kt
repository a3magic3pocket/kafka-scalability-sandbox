package com.example.demo.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.ZonedDateTime

@Entity
@Table(name = "order4")
data class Order4(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    var id: Long? = null,

    @Column(name = "user_name", nullable = false)
    var userName: String,

    @Column(name = "product_name", nullable = false)
    var productName: String,

    @Column(name = "quantity", nullable = false)
    var quantity: Double,

    @Column(name = "created_at", nullable = true, insertable = false, updatable = false)
    @CreationTimestamp
    var createdAt: ZonedDateTime? = null,

    @Column(name = "updated_at", nullable = true, insertable = true, updatable = true)
    @UpdateTimestamp
    var updatedAt: ZonedDateTime? = null,
)