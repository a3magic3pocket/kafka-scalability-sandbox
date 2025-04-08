package com.example.demo.repository

import com.example.demo.entity.Order2
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface Order2Repository : JpaRepository<Order2, Long>
