package com.example.demo.kafka.producer

import com.example.demo.constant.KafkaTopic
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service


@Service
class Inventory4Producer(
    private val objectMapper: ObjectMapper,
    private val kafkaTemplate: KafkaTemplate<String, String>
) {

    fun sendInventory4(quantity: Double) {
        kafkaTemplate.send(KafkaTopic.INVENTORY_4, quantity.toString())
    }

}