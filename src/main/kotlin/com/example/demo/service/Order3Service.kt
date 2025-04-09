package com.example.demo.service

import com.example.demo.dto.OrderRequestDto
import com.example.demo.dto.UpdateDto
import com.example.demo.entity.Order3
import com.example.demo.kafka.producer.Order3Producer
import com.example.demo.repository.Order3Repository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class Order3Service(
    private val order3Repository: Order3Repository,
    private val order3Producer: Order3Producer,
) {

    @Transactional(rollbackFor = [Exception::class])
    fun createOrder(orderRequestDto: OrderRequestDto): Order3 {
        val order = Order3(
            userName = orderRequestDto.userName,
            productName = orderRequestDto.productName,
            quantity = orderRequestDto.quantity,
        )

        return order3Repository.save(order)
    }

    @Transactional(rollbackFor = [Exception::class])
    fun updateOrder(orderUpdateDto: UpdateDto<OrderRequestDto>) {
        order3Producer.sendOrder3(orderUpdateDto)
    }

}