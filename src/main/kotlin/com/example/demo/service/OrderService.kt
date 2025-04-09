package com.example.demo.service

import com.example.demo.dto.AllOrdersDto
import com.example.demo.dto.DiffDto
import com.example.demo.repository.Order1Repository
import com.example.demo.repository.Order2Repository
import com.example.demo.repository.Order3Repository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Duration

@Service
class OrderService(
    private val order1Repository: Order1Repository,
    private val order2Repository: Order2Repository,
    private val order3Repository: Order3Repository,
) {

    @Transactional(readOnly = true)
    fun listAllOrders(): AllOrdersDto {

        return AllOrdersDto(
            order1 = order1Repository.findAll().map {
                DiffDto(
                    data = it,
                    diffSec = Duration.between(it.createdAt, it.updatedAt).seconds
                )
            },
            order2 = order2Repository.findAll().map {
                DiffDto(
                    data = it,
                    diffSec = Duration.between(it.createdAt, it.updatedAt).seconds
                )
            },
            order3 = order3Repository.findAll().map {
                DiffDto(
                    data = it,
                    diffSec = Duration.between(it.createdAt, it.updatedAt).seconds
                )
            },
        )
    }
}