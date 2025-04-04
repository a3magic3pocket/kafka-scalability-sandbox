package com.example.demo.dto

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