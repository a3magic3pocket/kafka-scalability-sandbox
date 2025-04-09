package com.example.demo.repository

import com.example.demo.entity.Inventory2
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface Inventory2Repository : JpaRepository<Inventory2, Long> {
    fun findTopByOrderByIdDesc(): Inventory2?
    fun findTopByOrderByIdAsc(): Inventory2?
}
