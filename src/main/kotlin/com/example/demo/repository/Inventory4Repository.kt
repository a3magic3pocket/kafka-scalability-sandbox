package com.example.demo.repository

import com.example.demo.entity.Inventory4
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface Inventory4Repository : JpaRepository<Inventory4, Long> {
    fun findTopByOrderByIdDesc(): Inventory4?
    fun findTopByOrderByIdAsc(): Inventory4?
}
