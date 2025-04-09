package com.example.demo.service

import com.example.demo.dto.OrderRequestDto
import com.example.demo.dto.UpdateDto
import com.example.demo.entity.Order2
import com.example.demo.kafka.producer.Order2Producer
import com.example.demo.repository.Order2Repository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class Order2Service(
    private val order2Repository: Order2Repository,
    private val order2Producer: Order2Producer,
) {

    @Transactional(rollbackFor = [Exception::class])
    fun createOrder(orderRequestDto: OrderRequestDto): Order2 {
        val order = Order2(
            userName = orderRequestDto.userName,
            productName = orderRequestDto.productName,
            quantity = orderRequestDto.quantity,
        )

        return order2Repository.save(order)
    }

    @Transactional(rollbackFor = [Exception::class])
    fun updateOrder(orderUpdateDto: UpdateDto<OrderRequestDto>) {
        order2Producer.sendOrder2(orderUpdateDto)
    }

}