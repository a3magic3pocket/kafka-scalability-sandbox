package com.example.demo.dto

data class UpdateDto<T>(
    val id: Long,
    val data: T
)

data class DiffDto<T>(
    val data: T,
    val diffSec: Long?
)