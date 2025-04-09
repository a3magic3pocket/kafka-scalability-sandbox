package com.example.demo.kafka.consumer

import com.example.demo.config.AppConfig
import com.example.demo.constant.KafkaTopic
import com.example.demo.dto.OrderRequestDto
import com.example.demo.dto.UpdateDto
import com.example.demo.repository.Order3Repository
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional


@Component
class Order3Consumer(
    private val objectMapper: ObjectMapper,
    private val order3Repository: Order3Repository,
) {

    @KafkaListener(
        topics = [KafkaTopic.ORDER_3],
        groupId = "order3",
        concurrency = "3",
        containerFactory = "kafkaListenerContainerFactory"
    )
    @Transactional(rollbackFor = [Exception::class], timeout = AppConfig.TRANSACTION_TIMEOUT)
    fun consume(record: ConsumerRecord<String, String>, acknowledgment: Acknowledgment) {
        val input = record.value()
        val orderUpdateDto: UpdateDto<OrderRequestDto> = objectMapper.readValue(
            input,
            object : TypeReference<UpdateDto<OrderRequestDto>>() {}
        )

        val orderRequestDto = orderUpdateDto.data

        val order = order3Repository.findByIdWithLock(orderUpdateDto.id)
            ?: throw RuntimeException("order not found")

        order.apply {
            this.quantity += orderRequestDto.quantity
        }

        order3Repository.save(order)

        if (AppConfig.USE_DELAY) {
            Thread.sleep(AppConfig.DELAY)
        }

        acknowledgment.acknowledge()
    }

}
