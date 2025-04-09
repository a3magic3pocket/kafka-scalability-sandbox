package com.example.demo.service

import com.example.demo.entity.Inventory1
import com.example.demo.repository.Inventory1Repository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class Inventory1Service(
    private val inventory1Repository: Inventory1Repository,
) {

    @Transactional(rollbackFor = [Exception::class])
    fun createInventory(quantity: Double): Inventory1 {
        val inventory = Inventory1(
            quantity = quantity,
        )

        return inventory1Repository.save(inventory)
    }

}