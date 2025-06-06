package com.example.demo.controller

import com.example.demo.dto.AllOrdersDto
import com.example.demo.dto.OrderRequestDto
import com.example.demo.dto.UpdateDto
import com.example.demo.entity.Order1
import com.example.demo.entity.Order2
import com.example.demo.entity.Order3
import com.example.demo.service.*
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.io.IOException

@RestController
class OrderController(
    private val orderService: OrderService,
    private val order1Service: Order1Service,
    private val order2Service: Order2Service,
    private val order3Service: Order3Service,
    private val order4Service: Inventory1Service,
) {
    companion object {
        const val TAG = "주문"
        const val ORDER_ID = "order_id"
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
        summary = "주문2 생성",
        description = "주문2 생성",
        tags = [TAG]
    )
    @ResponseStatus(HttpStatus.OK)
    @Throws(IOException::class)
    @PostMapping(value = ["/order2s"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createOrder2(
        @Valid @RequestBody orderRequestDto: OrderRequestDto
    ): ResponseEntity<Order2> {
        return ResponseEntity.ok().body(
            order2Service.createOrder(orderRequestDto)
        )
    }


    @Operation(
        summary = "주문2 갱신",
        description = "주문2 갱신",
        tags = [TAG]
    )
    @ResponseStatus(HttpStatus.OK)
    @Throws(IOException::class)
    @PutMapping(value = ["/order2s/{$ORDER_ID:[0-9]+}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateOrder2(
        @PathVariable(name = ORDER_ID) orderId: Long,
        @Valid @RequestBody orderRequestDto: OrderRequestDto
    ): ResponseEntity<String> {

        order2Service.updateOrder(
            UpdateDto(
                id = orderId,
                data = orderRequestDto
            )
        )
        return ResponseEntity.ok().body(
            "success"
        )
    }

    @Operation(
        summary = "주문3 생성",
        description = "주문3 생성",
        tags = [TAG]
    )
    @ResponseStatus(HttpStatus.OK)
    @Throws(IOException::class)
    @PostMapping(value = ["/order3s"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createOrder3(
        @Valid @RequestBody orderRequestDto: OrderRequestDto
    ): ResponseEntity<Order3> {
        return ResponseEntity.ok().body(
            order3Service.createOrder(orderRequestDto)
        )
    }


    @Operation(
        summary = "주문3 갱신",
        description = "주문3 갱신",
        tags = [TAG]
    )
    @ResponseStatus(HttpStatus.OK)
    @Throws(IOException::class)
    @PutMapping(value = ["/order3s/{$ORDER_ID:[0-9]+}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateOrder3(
        @PathVariable(name = ORDER_ID) orderId: Long,
        @Valid @RequestBody orderRequestDto: OrderRequestDto
    ): ResponseEntity<String> {

        order3Service.updateOrder(
            UpdateDto(
                id = orderId,
                data = orderRequestDto
            )
        )
        return ResponseEntity.ok().body(
            "success"
        )
    }

}
