package com.example.demo.service

import com.example.demo.kafka.producer.Inventory4Producer
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class Inventory4Service(
    private val inventory4Producer: Inventory4Producer,
) {

    @Transactional(rollbackFor = [Exception::class])
    fun createInventory(quantity: Double): String {
        inventory4Producer.sendInventory4(quantity)
        return "success"
    }

}