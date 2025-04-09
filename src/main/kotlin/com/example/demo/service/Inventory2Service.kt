package com.example.demo.service

import com.example.demo.kafka.producer.Inventory2Producer
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class Inventory2Service(
    private val inventory2Producer: Inventory2Producer,
) {

    @Transactional(rollbackFor = [Exception::class])
    fun createInventory(quantity: Double): String {
        inventory2Producer.sendInventory2(quantity)
        return "success"
    }

}