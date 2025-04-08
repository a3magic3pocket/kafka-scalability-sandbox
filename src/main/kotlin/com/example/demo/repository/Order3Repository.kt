package com.example.demo.repository

import com.example.demo.entity.Order3
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface Order3Repository : JpaRepository<Order3, Long>
