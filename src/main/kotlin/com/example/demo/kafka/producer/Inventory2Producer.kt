package com.example.demo.kafka.producer

import com.example.demo.constant.KafkaTopic
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service


@Service
class Inventory2Producer(
    private val kafkaTemplate: KafkaTemplate<String, String>
) {

    fun sendInventory2(quantity: Double) {
        kafkaTemplate.send(KafkaTopic.INVENTORY_2, quantity.toString())
    }

}