package com.example.demo.dto

import com.example.demo.entity.*

data class AllInventoryInfoDto(
    val inventory1: DiffDto<InventoryDetailDto<Inventory1>>,
    val inventory2: DiffDto<InventoryDetailDto<Inventory2>>,
    val inventory3: DiffDto<InventoryDetailDto<Inventory3>>,
    val inventory4: DiffDto<InventoryDetailDto<Inventory4>>,
)

data class InventoryDetailDto<T> (
    val firstInventory: T?,
    val lastInventory: T?,
    val count: Long,
)