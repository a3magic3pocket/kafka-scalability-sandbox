package com.example.demo.kafka.consumer

import com.example.demo.constant.KafkaTopic
import com.example.demo.entity.Inventory4
import com.example.demo.repository.Inventory4Repository
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional


@Component
class Inventory4Consumer(
    private val inventory4Repository: Inventory4Repository,
) {

    @KafkaListener(
        topics = [KafkaTopic.INVENTORY_4],
        groupId = "inventory4",
        concurrency = "3",
        containerFactory = "batchKafkaListenerContainerFactory"
    )
    @Transactional(rollbackFor = [Exception::class])
    fun consume(records: List<String>, acknowledgment: Acknowledgment) {
//        println("records++" + records)
        val inventories: MutableList<Inventory4> = mutableListOf()
        for (input in records) {
            val quantity = input.toDouble()
            val inventory = Inventory4(
                quantity = quantity
            )
            inventories.add(inventory)
        }

        inventory4Repository.saveAll(inventories)

        acknowledgment.acknowledge()
    }

}
