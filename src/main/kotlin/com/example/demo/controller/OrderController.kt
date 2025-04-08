package com.example.demo.controller

import com.example.demo.dto.AllOrdersDto
import com.example.demo.dto.OrderRequestDto
import com.example.demo.dto.UpdateDto
import com.example.demo.entity.Order1
import com.example.demo.service.Order1Service
import com.example.demo.service.OrderService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.io.IOException

@RestController
class OrderController(
    private val order1Service: Order1Service,
    private val orderService: OrderService
) {
    companion object {
        const val TAG = "주문"
        const val ORDER_ID = "order_id"
    }

    @Operation(
        summary = "주문1 생성",
        description = "주문1 생성",
        tags = [TAG]
    )
    @ResponseStatus(HttpStatus.OK)
    @Throws(IOException::class)
    @PostMapping(value = ["/order1s"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createOrder1(
        @Valid @RequestBody orderRequestDto: OrderRequestDto
    ): ResponseEntity<Order1> {
        return ResponseEntity.ok().body(
            order1Service.createOrder(orderRequestDto)
        )
    }

    @Operation(
        summary = "주문1 갱신",
        description = "주문1 갱신",
        tags = [TAG]
    )
    @ResponseStatus(HttpStatus.OK)
    @Throws(IOException::class)
    @PutMapping(value = ["/order1s/{$ORDER_ID:[0-9]+}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateOrder1(
        @PathVariable(name = ORDER_ID) orderId: Long,
        @Valid @RequestBody orderRequestDto: OrderRequestDto
    ): ResponseEntity<Order1> {
        return ResponseEntity.ok().body(
            order1Service.updateOrder(
                UpdateDto(
                    id = orderId,
                    data = orderRequestDto
                )
            )
        )
    }

    @Operation(
        summary = "모든 주문 목록 조회",
        description = "모든 주문 목록 조회",
        tags = [TAG]
    )
    @ResponseStatus(HttpStatus.OK)
    @Throws(IOException::class)
    @GetMapping(value = ["/orders"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun listAllOrders(): ResponseEntity<AllOrdersDto> {
        return ResponseEntity.ok().body(
            orderService.listAllOrders()
        )
    }

}
