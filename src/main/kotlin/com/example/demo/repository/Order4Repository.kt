package com.example.demo.repository

import com.example.demo.entity.Order4
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface Order4Repository : JpaRepository<Order4, Long>
