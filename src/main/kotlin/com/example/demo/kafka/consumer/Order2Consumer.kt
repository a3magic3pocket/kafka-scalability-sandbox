package com.example.demo.kafka.consumer

import com.example.demo.config.AppConfig
import com.example.demo.constant.KafkaTopic
import com.example.demo.dto.OrderRequestDto
import com.example.demo.dto.UpdateDto
import com.example.demo.repository.Order2Repository
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.data.repository.findByIdOrNull
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component


@Component
class Order2Consumer(
    private val objectMapper: ObjectMapper,
    private val order2Repository: Order2Repository,
) {

    @KafkaListener(
        topics = [KafkaTopic.ORDER_2],
        groupId = "order2",
        concurrency = "1",
        containerFactory = "kafkaListenerContainerFactory"
    )
    fun consume(record: ConsumerRecord<String, String>, acknowledgment: Acknowledgment) {
        val input = record.value()
        val orderUpdateDto: UpdateDto<OrderRequestDto> = objectMapper.readValue(
            input,
            object : TypeReference<UpdateDto<OrderRequestDto>>() {}
        )

        val orderRequestDto = orderUpdateDto.data
        val order = order2Repository.findByIdOrNull(id = orderUpdateDto.id)
            ?: throw RuntimeException("order2 not found")

        order.apply {
            this.quantity += orderRequestDto.quantity
        }

        order2Repository.save(order)

        if (AppConfig.USE_DELAY) {
            Thread.sleep(AppConfig.DELAY)
        }

        acknowledgment.acknowledge()
    }

}
