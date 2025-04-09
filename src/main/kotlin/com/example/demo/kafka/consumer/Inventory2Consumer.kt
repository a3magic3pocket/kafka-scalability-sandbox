package com.example.demo.kafka.consumer

import com.example.demo.constant.KafkaTopic
import com.example.demo.entity.Inventory2
import com.example.demo.repository.Inventory2Repository
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component


@Component
class Inventory2Consumer(
    private val inventory2Repository: Inventory2Repository,
) {

    @KafkaListener(
        topics = [KafkaTopic.INVENTORY_2],
        groupId = "inventory2",
        concurrency = "1",
        containerFactory = "kafkaListenerContainerFactory"
    )
    fun consume(record: ConsumerRecord<String, String>, acknowledgment: Acknowledgment) {
        val input = record.value()
        val quantity = input.toDouble()

        val inventory = Inventory2(
            quantity = quantity,
        )

        inventory2Repository.save(inventory)

        acknowledgment.acknowledge()
    }

}
