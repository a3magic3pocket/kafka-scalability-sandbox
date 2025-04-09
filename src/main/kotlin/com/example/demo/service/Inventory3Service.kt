package com.example.demo.service

import com.example.demo.kafka.producer.Inventory3Producer
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class Inventory3Service(
    private val inventory3Producer: Inventory3Producer
) {

    @Transactional(rollbackFor = [Exception::class])
    fun createInventory(quantity: Double): String {
        inventory3Producer.sendInventory3(quantity)
        return "success"
    }

}