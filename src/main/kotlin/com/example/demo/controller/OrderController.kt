package com.example.demo.controller

import com.example.demo.dto.OrderRequestDto
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.io.IOException

@RestController
class OrderController {
    companion object {
        const val TAG = "주문"
    }

    @Operation(
        summary = "주문 생성",
        description = "주문을 생성한다.",
        tags = [TAG]
    )
    @ResponseStatus(HttpStatus.OK)
    @Throws(IOException::class)
    @PostMapping(value = ["/order"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createOrder(
        @Valid @RequestBody orderRequestDto: OrderRequestDto
    ): String {
        return "Hello world"
//        return ResponseEntity.ok().body(authDto)
    }

}
