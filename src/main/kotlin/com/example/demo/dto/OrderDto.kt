package com.example.demo.dto

import com.example.demo.entity.Order1
import com.example.demo.entity.Order2
import com.example.demo.entity.Order3
import com.example.demo.entity.Order4
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty

data class OrderRequestDto(
    @field:NotEmpty(message = "필수값 입니다")
    val userName: String,

    @field:NotEmpty(message = "필수값 입니다")
    val productName: String,

    @field:Min(value = 0, message = "0보다 크거나 같아야 합니다")
    val quantity: Double,
)

data class AllOrdersDto(
    val order1: List<Order1>,
    val order2: List<Order2>,
    val order3: List<Order3>,
    val order4: List<Order4>,
)