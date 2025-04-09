package com.example.demo.repository

import com.example.demo.entity.Inventory3
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface Inventory3Repository : JpaRepository<Inventory3, Long> {
    fun findTopByOrderByIdDesc(): Inventory3?
    fun findTopByOrderByIdAsc(): Inventory3?
}
