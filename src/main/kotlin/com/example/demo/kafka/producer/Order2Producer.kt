package com.example.demo.kafka.producer

import com.example.demo.constant.KafkaTopic
import com.example.demo.dto.OrderRequestDto
import com.example.demo.dto.UpdateDto
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service


@Service
class Order2Producer(
    private val objectMapper: ObjectMapper,
    private val kafkaTemplate: KafkaTemplate<String, String>
) {

    fun sendOrder2(orderUpdateDto: UpdateDto<OrderRequestDto>) {
        val message = objectMapper.writeValueAsString(orderUpdateDto)

        kafkaTemplate.send(KafkaTopic.ORDER_2, message)
    }

}