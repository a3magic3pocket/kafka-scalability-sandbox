package com.example.demo.repository

import com.example.demo.entity.Order1
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface Order1Repository : JpaRepository<Order1, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT o FROM Order1 o WHERE o.id = :id")
    fun findByIdWithLock(@Param("id") id: Long): Order1?
}
