package com.example.demo.repository

import com.example.demo.entity.Inventory1
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface Inventory1Repository : JpaRepository<Inventory1, Long> {

    fun findTopByOrderByIdDesc(): Inventory1?
    fun findTopByOrderByIdAsc(): Inventory1?

}