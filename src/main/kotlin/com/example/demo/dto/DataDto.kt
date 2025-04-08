package com.example.demo.dto

data class UpdateDto<T>(
    val id: Long,
    val data: T
)