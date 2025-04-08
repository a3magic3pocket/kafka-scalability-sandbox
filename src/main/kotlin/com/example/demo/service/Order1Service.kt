package com.example.demo.service

import com.example.demo.dto.OrderRequestDto
import com.example.demo.dto.UpdateDto
import com.example.demo.entity.Order1
import com.example.demo.repository.Order1Repository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class Order1Service(
    private val order1Repository: Order1Repository,
) {

    @Transactional(rollbackFor = [Exception::class])
    fun createOrder(orderRequestDto: OrderRequestDto): Order1 {
        val order = Order1(
            userName = orderRequestDto.userName,
            productName = orderRequestDto.productName,
            quantity = orderRequestDto.quantity,
        )

        return order1Repository.save(order)
    }

    @Transactional(rollbackFor = [Exception::class], timeout = 5)
    fun updateOrder(orderUpdateDto: UpdateDto<OrderRequestDto>): Order1 {
        val savedOrder = order1Repository.findByIdWithLock(orderUpdateDto.id)
            ?: throw RuntimeException("order not found")

        savedOrder.apply {
            quantity += orderUpdateDto.data.quantity
        }

        return order1Repository.save(savedOrder)
    }

}