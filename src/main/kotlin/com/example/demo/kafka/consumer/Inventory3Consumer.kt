package com.example.demo.kafka.consumer

import com.example.demo.constant.KafkaTopic
import com.example.demo.entity.Inventory3
import com.example.demo.repository.Inventory3Repository
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component


@Component
class Inventory3Consumer(
    private val inventory3Repository: Inventory3Repository,
) {

    @KafkaListener(
        topics = [KafkaTopic.INVENTORY_3],
        groupId = "inventory3",
        concurrency = "3",
        containerFactory = "kafkaListenerContainerFactory"
    )
    fun consume(record: ConsumerRecord<String, String>, acknowledgment: Acknowledgment) {
        val input = record.value()
        val quantity = input.toDouble()

        val inventory = Inventory3(
            quantity = quantity,
        )

        inventory3Repository.save(inventory)

        acknowledgment.acknowledge()
    }

}
